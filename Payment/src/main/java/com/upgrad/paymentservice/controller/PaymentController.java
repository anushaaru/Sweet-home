package com.upgrad.paymentservice.controller;

import com.upgrad.paymentservice.entity.TransactionDetailsEntity;
import com.upgrad.paymentservice.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    /**
     *Endpoint 1: /payment/transaction
     */

    @PostMapping(value="/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public int makePayment(@RequestBody TransactionDetailsEntity transactionDetailsEntity){

        return paymentService.getTransactionId(transactionDetailsEntity);

    }

    /**
     * Endpoint 2: /payment/transaction/{transactionId}
     */
    @GetMapping(value="/transaction/{transactionId}")
    public ResponseEntity getPaymentById(@PathVariable ("transactionId") int transactionId) {

        TransactionDetailsEntity transactionDetailsEntity = paymentService.getTransactionDetails(transactionId);

        return new ResponseEntity(transactionDetailsEntity, HttpStatus.OK);

    }


}
