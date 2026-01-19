package com.example.payment.Repository;

import com.example.payment.Models.Payment;
import com.example.payment.Enum.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrder_OrderId(Long orderId);
    Optional<Payment> findByTransactionId(String transactionId);
    List<Payment> findByStatus(PaymentStatus status);
    boolean existsByOrder_OrderId(Long orderId);
}
