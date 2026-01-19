package com.example.payment.Controller;

import com.example.payment.Dto.Request.PaymentRequest;
import com.example.payment.Dto.Response.PaymentResponse;
import com.example.payment.Service.PaymentService;
import com.example.payment.Utility.ResponseBuilder;
import com.example.payment.Utility.ResponseStructure;
import com.example.payment.Utility.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ResponseStructure<PaymentResponse>> createPayment(@RequestBody PaymentRequest request) {
        return ResponseBuilder.success(HttpStatus.CREATED, "Payment Created !!", paymentService.createPayment(request));
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<PaymentResponse>> getPaymentById(@RequestParam Long paymentId) {
        return ResponseBuilder.success(HttpStatus.FOUND, "Payment Found !!",paymentService.getPaymentById(paymentId));
    }

    @GetMapping("/order")
    public ResponseEntity<ResponseStructure<PaymentResponse>> getPaymentByOrderId(@RequestParam Long orderId) {
        return ResponseBuilder.success(HttpStatus.FOUND, "Payment Found !!",paymentService.getPaymentByOrderId(orderId));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<ResultDto<PaymentResponse>>> getAllPayments() {
        return ResponseBuilder.success(HttpStatus.FOUND, "Payments Fetched !!",paymentService.getAllPayments());
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<PaymentResponse>> updatePayment(
            @RequestParam Long paymentId,
            @RequestBody PaymentRequest request) {
        return ResponseBuilder.success(HttpStatus.OK, "Payment Updated !!", paymentService.updatePayment(paymentId, request));
    }

    @DeleteMapping
    public ResponseEntity<ResponseStructure<PaymentResponse>> deletePayment(@RequestParam Long paymentId) {
        return ResponseBuilder.success(HttpStatus.OK, "Payment Deleted !!", paymentService.deletePayment(paymentId));
    }
}
