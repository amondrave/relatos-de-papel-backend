package com.relatosdepapel.payments.api;

import com.relatosdepapel.payments.core.BadRequestException;
import com.relatosdepapel.payments.core.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

 @ExceptionHandler(NotFoundException.class)
 public ResponseEntity<Map<String, Object>> notFound(NotFoundException ex) {
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
      "timestamp", Instant.now().toString(),
      "error", "NOT_FOUND",
      "message", ex.getMessage()
  ));
 }

 @ExceptionHandler(BadRequestException.class)
 public ResponseEntity<Map<String, Object>> badRequest(BadRequestException ex) {
  return ResponseEntity.badRequest().body(Map.of(
      "timestamp", Instant.now().toString(),
      "error", "BAD_REQUEST",
      "message", ex.getMessage()
  ));
 }

 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException ex) {
  Map<String, String> fieldErrors = new HashMap<>();
  for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
   fieldErrors.put(fe.getField(), fe.getDefaultMessage());
  }
  return ResponseEntity.badRequest().body(Map.of(
      "timestamp", Instant.now().toString(),
      "error", "VALIDATION_ERROR",
      "message", "Solicitud inválida",
      "fields", fieldErrors
  ));
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<Map<String, Object>> generic(Exception ex) {
  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
      "timestamp", Instant.now().toString(),
      "error", "INTERNAL_ERROR",
      "message", ex.getMessage()
  ));
 }
}
