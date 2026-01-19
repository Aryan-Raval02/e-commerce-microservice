package com.example.payment.Service;

import com.example.payment.Dto.Request.PaymentRequest;
import com.example.payment.Dto.Response.PaymentResponse;
import com.example.payment.Utility.ResultDto;

import java.util.List;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request);

    PaymentResponse getPaymentById(Long paymentId);

    PaymentResponse getPaymentByOrderId(Long orderId);

    ResultDto<PaymentResponse> getAllPayments();

    PaymentResponse updatePayment(Long paymentId, PaymentRequest request);

    PaymentResponse deletePayment(Long paymentId);
}
