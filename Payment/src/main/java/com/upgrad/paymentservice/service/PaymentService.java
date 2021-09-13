package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.entity.TransactionDetailsEntity;

public interface PaymentService {

    public int getTransactionId(TransactionDetailsEntity transactionDetailsEntity);

    public TransactionDetailsEntity getTransactionDetails(int transactionId) throws Exception;



}
