package com.upgrad.booking.Booking.feign;

import com.upgrad.booking.Booking.entity.TransactionDetailsEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "API-GATEWAY")
public interface PaymentServiceClient {

    @RequestMapping(value = "/payment/transaction", method = RequestMethod.POST)
    public int makePayment(@RequestBody TransactionDetailsEntity transactionDetailsEntity);
}
