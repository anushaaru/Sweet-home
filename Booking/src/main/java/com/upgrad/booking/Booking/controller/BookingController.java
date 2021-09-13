package com.upgrad.booking.Booking.controller;

import com.upgrad.booking.Booking.entity.BookingInfoEntity;
import com.upgrad.booking.Booking.entity.TransactionDetailsEntity;
import com.upgrad.booking.Booking.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class BookingController {


    @Autowired
    private BookService bookService;

    @PostMapping(value="/booking", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBooking(@RequestBody BookingInfoEntity bookingInfoEntity){

        BookingInfoEntity savedBooking = bookService.addBooking(bookingInfoEntity);

        return new ResponseEntity(savedBooking, HttpStatus.CREATED);

    }

    @PostMapping(value="booking/{bookingId}/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity confirmBookingPayment(@RequestBody TransactionDetailsEntity transactionDetailsEntity, @PathVariable("bookingId") int bookingId){

        BookingInfoEntity savedBooking = bookService.getPaymentDetails(bookingId, transactionDetailsEntity);

        return new ResponseEntity(savedBooking, HttpStatus.CREATED);
    }


}
