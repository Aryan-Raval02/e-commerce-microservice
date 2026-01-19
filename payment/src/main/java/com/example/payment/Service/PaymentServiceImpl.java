package com.example.payment.Service;

import com.example.payment.Dto.Request.PaymentRequest;
import com.example.payment.Dto.Response.PaymentResponse;
import com.example.payment.Enum.PaymentStatus;
import com.example.payment.Exception.ResourceFoundException;
import com.example.payment.Exception.ResourceNotFoundException;
import com.example.payment.Models.Order;
import com.example.payment.Models.Payment;
import com.example.payment.Repository.OrderRepository;
import com.example.payment.Repository.PaymentRepository;
import com.example.payment.Utility.ResultDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order Not Found !!"));

        if (paymentRepository.existsByOrder_OrderId(order.getOrderId())) {
            throw new ResourceFoundException("Payment Already Exists For This User !!");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus(request.getStatus());
        payment.setTransactionId(request.getTransactionId());

        Payment savedPayment = paymentRepository.save(payment);

        if (request.getStatus() == PaymentStatus.SUCCESS) {
            order.setStatus("PAID");
            orderRepository.save(order);
        }

        return toResponse(savedPayment);
    }

    @Override
    public PaymentResponse getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Not Found !!"));
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrder_OrderId(orderId)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Not Found For This Order !!"));
    }

    @Override
    public ResultDto<PaymentResponse> getAllPayments() {
        List<PaymentResponse> list = paymentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

        ResultDto<PaymentResponse> result = new ResultDto<>();

        result.setList(list);
        result.setTotal(list.size());

        return result;
    }

    @Override
    @Transactional
    public PaymentResponse updatePayment(Long paymentId, PaymentRequest request) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Not Found !!"));

        payment.setMethod(request.getMethod());
        payment.setStatus(request.getStatus());
        payment.setTransactionId(request.getTransactionId());

        Payment updated = paymentRepository.save(payment);

        if (request.getStatus() == PaymentStatus.SUCCESS) {
            Order order = payment.getOrder();
            order.setStatus("PAID");
            orderRepository.save(order);
        }

        return toResponse(updated);
    }

    @Override
    @Transactional
    public PaymentResponse deletePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Not Found !!"));

        paymentRepository.delete(payment);

        return toResponse(payment);
    }

    private PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getOrder().getOrderId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus(),
                payment.getTransactionId(),
                payment.getCreatedAt()
        );
    }
}
