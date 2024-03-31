package com.hrs.user.service.external.service;

import com.hrs.user.service.entites.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    // create
    @PostMapping("/ratings")
    Rating createRating(Rating value);
    // read

    // put
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    // delete
}
