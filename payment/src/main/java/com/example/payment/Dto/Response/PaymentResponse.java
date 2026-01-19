package com.example.payment.Dto.Response;

import com.example.payment.Enum.PaymentStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long paymentId;
    private Long orderId;
    private BigDecimal amount;
    private String method;
    private PaymentStatus status;
    private String transactionId;
    private LocalDateTime createdAt;
}
