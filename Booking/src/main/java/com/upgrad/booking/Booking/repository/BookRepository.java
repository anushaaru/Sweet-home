package com.upgrad.booking.Booking.repository;

import com.upgrad.booking.Booking.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookingInfoEntity, Integer> {
}
