package com.ankit.userservice.client;

import com.ankit.userservice.entity.Rating;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Component
public class RatingClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rating.service.baseUrl}")
    private  String baseUrl;

    public List<Rating> getRatingByUserId(String userId){
        Rating[] ratings = restTemplate.getForObject(baseUrl+"users/"+userId,Rating[].class);
        return Arrays.stream(ratings).toList();
    }
}
