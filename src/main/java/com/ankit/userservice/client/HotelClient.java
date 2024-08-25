package com.ankit.userservice.client;


import com.ankit.userservice.entity.Hotel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@NoArgsConstructor
@Component
public class HotelClient {
    @Autowired
    private  RestTemplate restTemplate;

    @Value("${hotel.service.baseUrl}")
    private  String baseUrl;

    public Hotel getHotel(String hotelId){
        return restTemplate.getForObject(baseUrl+hotelId,Hotel.class);
    }
}
