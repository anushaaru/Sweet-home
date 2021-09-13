package com.upgrad.booking.Booking.service;

import com.upgrad.booking.Booking.entity.BookingInfoEntity;
import com.upgrad.booking.Booking.entity.TransactionDetailsEntity;

import java.util.ArrayList;

public interface BookService {

    public  ArrayList<String> getRandomNumbers(int count);

    public BookingInfoEntity addBooking(BookingInfoEntity bookingInfoEntity);

    public BookingInfoEntity getPaymentDetails(int bookingId, TransactionDetailsEntity paymentDetails);
}
