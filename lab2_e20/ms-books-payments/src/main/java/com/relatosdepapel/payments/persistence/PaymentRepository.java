package com.relatosdepapel.payments.persistence;

import com.relatosdepapel.payments.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
