package com.upgrad.paymentservice.repository;

import com.upgrad.paymentservice.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetailsEntity, Integer> {
}
