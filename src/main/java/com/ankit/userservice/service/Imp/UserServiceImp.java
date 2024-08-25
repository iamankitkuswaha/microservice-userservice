package com.ankit.userservice.service.Imp;

import com.ankit.userservice.client.HotelClient;
import com.ankit.userservice.client.RatingClient;
import com.ankit.userservice.entity.Hotel;
import com.ankit.userservice.entity.Rating;
import com.ankit.userservice.entity.User;
import com.ankit.userservice.repository.UserRepository;
import com.ankit.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelClient hotelClient;

    @Autowired
    private RatingClient ratingClient;
    @Override
    public User saveUser(User user) {

        // Generate unique user Id
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(user->{
            String userId = user.getUserId();
            List<Rating> ratings = getRatingList(userId);
            user.setRatingList(ratings);
            return user;
        }).collect(Collectors.toList());
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Rating>ratings = getRatingList(user.getUserId());
        log.info("Rating List: {}",ratings);
        user.setRatingList(ratings);
        return userRepository.findById(userId).orElseThrow();
    }

    public List<Rating> getRatingList(String userId){
        return ratingClient.getRatingByUserId(userId).stream().map(rating->{
            Hotel hotel = hotelClient.getHotel(rating.getHotelId());
            log.info("Hotel: {}",hotel);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
    }
}
