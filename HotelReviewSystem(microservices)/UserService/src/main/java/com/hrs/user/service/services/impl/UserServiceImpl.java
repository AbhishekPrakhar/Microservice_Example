package com.hrs.user.service.services.impl;

import com.hrs.user.service.entites.Hotel;
import com.hrs.user.service.entites.Rating;
import com.hrs.user.service.entites.User;
import com.hrs.user.service.exceptions.ResourceNotFoundException;
import com.hrs.user.service.external.service.HotelService;
import com.hrs.user.service.repositories.UserRepository;
import com.hrs.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // generating unique id for user
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList =  userRepository.findAll();

        return userList;
    }

    @Override
    public User getUser(String userId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with the given id" +userId));
        // fetch rating for above service from RATING-SERVICE
        // http://localhost:8083/ratings/users/<userId>
        Rating[] ratingForUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingForUser);

        List<Rating> ratings = Arrays.stream(ratingForUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service http://localhost:8082/hotels/0d78fd78-ea16-4ada-9ae6-40327d116b39
            // set the hotel to rating
            // return the rating
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();

            // Feign Client is used here
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

//            logger.info("response status code: {}", forEntity.getStatusCode());

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}
