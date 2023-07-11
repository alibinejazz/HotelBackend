package com.mock.exotourista.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mock.exotourista.Model.Hotel;


public interface HotelRepo extends JpaRepository<Hotel, Long> {
    
}
