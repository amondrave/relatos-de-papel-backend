package com.relatospapel.orders.service;

import com.relatospapel.orders.data.OrderJpaRepository;
import com.relatospapel.orders.data.model.Payment;
import com.relatospapel.orders.facade.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.relatospapel.orders.facade.BooksFacade;
import com.relatospapel.orders.controller.model.PaymentRequest;

@Service
public class PaymentsServiceImpl implements PaymentsService {

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private BooksFacade booksFacade;

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private OrderJpaRepository repository;

  @Override
  public Payment createPayment(PaymentRequest request) {

    List<Book> books = request.getBooks().stream().map(booksFacade::getBook).filter(Objects::nonNull).toList();

    if(books.size() != request.getBooks().size() || books.stream().anyMatch(book -> !book.getVisible())) {
      return null;
    } else {
      Payment payment = Payment.builder().books(books.stream().map(Book::getId).collect(Collectors.toList())).build();
      repository.save(payment);
      return payment;
    }
  }

  @Override
  public Payment getPayment(String id) {
    return repository.findById(Long.valueOf(id)).orElse(null);
  }

  @Override
  public List<Payment> getPayments() {
    List<Payment> payments = repository.findAll();
    return payments.isEmpty() ? null : payments;
  }
}
