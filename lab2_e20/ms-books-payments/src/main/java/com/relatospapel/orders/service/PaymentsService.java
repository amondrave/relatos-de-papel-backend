package com.relatospapel.orders.service;

import com.relatospapel.orders.data.model.Payment;
import com.relatospapel.orders.controller.model.PaymentRequest;
import java.util.List;

public interface PaymentsService {
	
	Payment createPayment(PaymentRequest request);

	Payment getPayment(String id);

	List<Payment> getPayments();

}
