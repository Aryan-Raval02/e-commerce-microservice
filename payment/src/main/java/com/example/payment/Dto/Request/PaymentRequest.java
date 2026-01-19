package com.example.payment.Dto.Request;

import com.example.payment.Enum.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private Long orderId;
    private BigDecimal amount;
    private String method;          // UPI, CARD, NET_BANKING
    private PaymentStatus status;   // SUCCESS / FAILED
    private String transactionId;
}
