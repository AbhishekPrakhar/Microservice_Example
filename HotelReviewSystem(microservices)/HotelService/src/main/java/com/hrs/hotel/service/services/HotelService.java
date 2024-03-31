package com.hrs.hotel.service.services;

import com.hrs.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel create(Hotel hotel);

    //getAll
    List<Hotel> getAll();

    //get single
    Hotel get(String id);
}
