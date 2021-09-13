package com.upgrad.booking.Booking.exception.handler;

import com.upgrad.booking.Booking.dto.ErrorResponse;
import com.upgrad.booking.Booking.exception.PaymentFailedException;
import com.upgrad.booking.Booking.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

    private String NO_RECORDS_FOUND = "NO_RECORDS_FOUND";

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException e, WebRequest req){

        List<String> errorDetails = new ArrayList<String>();
        errorDetails.add(e.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentFailedException.class)
    public final ResponseEntity<ErrorResponse> handlePaymentFailedException(PaymentFailedException e, WebRequest req){

        List<String> errorDetails = new ArrayList<String>();
        errorDetails.add(e.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
