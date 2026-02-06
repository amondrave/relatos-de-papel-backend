package com.relatospapel.orders.data;

import com.relatospapel.orders.data.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Payment, Long> {
}
