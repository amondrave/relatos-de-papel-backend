package com.relatosdepapel.payments.service;

import com.relatosdepapel.payments.api.dto.PaymentRequest;
import com.relatosdepapel.payments.api.dto.PaymentResponse;
import com.relatosdepapel.payments.core.BadRequestException;
import com.relatosdepapel.payments.core.NotFoundException;
import com.relatosdepapel.payments.domain.Payment;
import com.relatosdepapel.payments.integration.catalogue.Book;
import com.relatosdepapel.payments.integration.catalogue.BooksFacade;
import com.relatosdepapel.payments.persistence.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentsService {

 private final PaymentRepository repo;
 private final BooksFacade booksFacade;

 @Transactional
 public PaymentResponse create(PaymentRequest request) {

  // 1) Consultar catálogo para cada id recibido
  List<Book> books = request.books().stream()
      .map(booksFacade::getBook)
      .filter(Objects::nonNull)
      .toList();

  // 2) Business validation: must exist, be visible and have stock (>0)
  boolean missing = books.size() != request.books().size();
  boolean notVisible = books.stream().anyMatch(b -> b.getVisible() != null && !b.getVisible());
  boolean noStock = books.stream().anyMatch(b -> b.getStock() != null && b.getStock() <= 0);

  if (missing || notVisible || noStock) {
   throw new BadRequestException("Compra rechazada: uno o más libros no existen, no son visibles o no tienen stock");
  }

  // 3) Persistir acuse de compra en DB del operador (H2 independiente)
  List<Long> ids = books.stream().map(Book::getId).toList();
  Payment payment = Payment.builder()
      .books(ids)
      .customer(request.customer())
      .build();

  Payment saved = repo.save(payment);
  return new PaymentResponse(saved.getId(), saved.getBooks(), saved.getCustomer(), saved.getCreatedAt());
 }

 @Transactional(readOnly = true)
 public List<PaymentResponse> list() {
  return repo.findAll().stream()
      .map(p -> new PaymentResponse(p.getId(), p.getBooks(), p.getCustomer(), p.getCreatedAt()))
      .toList();
 }

 @Transactional(readOnly = true)
 public PaymentResponse get(Long id) {
  Payment p = repo.findById(id).orElseThrow(() -> new NotFoundException("Compra no encontrada: " + id));
  return new PaymentResponse(p.getId(), p.getBooks(), p.getCustomer(), p.getCreatedAt());
 }
}
