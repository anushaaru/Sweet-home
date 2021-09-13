package com.upgrad.paymentservice.exception.handler;

import com.upgrad.paymentservice.dto.ErrorResponse;
import com.upgrad.paymentservice.exception.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class PaymentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(TransactionNotFoundException e, WebRequest req){

        List<String> errorDetails = new ArrayList<String>();
        errorDetails.add(e.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}
