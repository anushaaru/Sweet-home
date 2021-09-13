package com.upgrad.booking.Booking.service;

import com.upgrad.booking.Booking.entity.BookingInfoEntity;
import com.upgrad.booking.Booking.entity.TransactionDetailsEntity;
import com.upgrad.booking.Booking.exception.PaymentFailedException;
import com.upgrad.booking.Booking.exception.RecordNotFoundException;
import com.upgrad.booking.Booking.feign.PaymentServiceClient;
import com.upgrad.booking.Booking.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Autowired
    private BookRepository bookRepository;

    @Value("${paymentManagement.url}")
    private String paymentManagementUrl;

    private Date date = new Date();

    private String message = "Booking confirmed for user with aadhaar number: ";

    @Override
    public ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }

    @Override
    public BookingInfoEntity addBooking(BookingInfoEntity bookingInfoEntity) {

        ArrayList<String> rooms = getRandomNumbers(bookingInfoEntity.getNumOfRooms());
        String roomNo = "";
        for(int i = 0; i<rooms.size(); i++){
            roomNo = roomNo + rooms.get(i);
            if(i != rooms.size()-1){
                roomNo = roomNo + ",";
            }
        }
        bookingInfoEntity.setRoomNumbers(roomNo);

        long date1InMs = bookingInfoEntity.getFromDate().getTime();
        long date2InMs = bookingInfoEntity.getToDate().getTime();

        long timeDiff = 0;
        if(date1InMs > date2InMs) {
            timeDiff = date1InMs - date2InMs;
        } else {
            timeDiff = date2InMs - date1InMs;
        }

        int noOfDays = (int) (timeDiff / (1000 * 60 * 60* 24));;

        int roomPrice = 1000 * noOfDays * bookingInfoEntity.getNumOfRooms();
        bookingInfoEntity.setRoomPrice(roomPrice);
        bookingInfoEntity.setBookedOn(date);
        return bookRepository.save(bookingInfoEntity);
    }

    @Override
    public BookingInfoEntity getPaymentDetails(int bookingId, TransactionDetailsEntity transactionDetailsEntity) {

        BookingInfoEntity bookingInfoEntity = bookRepository.findById(bookingId).orElseThrow(() -> new RecordNotFoundException(" Invalid Booking Id "));

        if(transactionDetailsEntity.getPaymentMode().equals("UPI") || transactionDetailsEntity.getPaymentMode().equals("CARD")){

        }
        else{
            throw new RecordNotFoundException("Invalid mode of payment");
        }
        Map<String,TransactionDetailsEntity> paymentUriMap = new HashMap<>();
        paymentUriMap.put("transactionId",transactionDetailsEntity);
        int transactionId = restTemplate.postForObject(paymentManagementUrl,transactionDetailsEntity, Integer.class, paymentUriMap);

        //int transactionId = paymentServiceClient.makePayment(transactionDetailsEntity);
        if(transactionId > 0){
            bookingInfoEntity.setTransactionId(transactionId);
            bookRepository.save(bookingInfoEntity);
            System.out.println(message+ " "+ bookingInfoEntity.getAadharNumber() + " "+  "Here are the booking details:    " +bookingInfoEntity.toString());

            return bookingInfoEntity;
        }
        else{
            throw new PaymentFailedException("Failed to make Payment: not able to fetch transaction details");

        }
    }


}
