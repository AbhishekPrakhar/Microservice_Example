package com.hrs.rating.RatingService.services;

import com.hrs.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get All ratings
    List<Rating> getRatings();

    //get all by userId
    List<Rating> getRatingByUserId(String userId);

    //get all by HotelID
    List<Rating> getRatingByHotelId(String hotelId);
}
