package com.relatosdepapel.payments.api;

import com.relatosdepapel.payments.api.dto.PatchPaymentRequest;
import com.relatosdepapel.payments.api.dto.PaymentRequest;
import com.relatosdepapel.payments.api.dto.PaymentResponse;
import com.relatosdepapel.payments.api.dto.UpdatePaymentRequest;
import com.relatosdepapel.payments.service.PaymentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentsController {

 private final PaymentsService service;

 /**
  * Registers a purchase after validating books in the catalogue service.
   */
 @PostMapping
 @ResponseStatus(HttpStatus.CREATED)
 public PaymentResponse create(@RequestBody @Valid PaymentRequest request) {
  return service.create(request);
 }

 @GetMapping
 public List<PaymentResponse> list() {
  return service.list();
 }

 @GetMapping("/{id}")
 public PaymentResponse get(@PathVariable Long id) {
  return service.get(id);
 }

@PutMapping("/{id}")
public PaymentResponse update(@PathVariable Long id,@RequestBody @Valid UpdatePaymentRequest request) {
    return service.update(id, request);
}

@PatchMapping("/{id}")
public PaymentResponse patch(@PathVariable Long id,@RequestBody PatchPaymentRequest request) {
    return service.patch(id, request);
}

@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(@PathVariable Long id) {service.delete(id);}
}