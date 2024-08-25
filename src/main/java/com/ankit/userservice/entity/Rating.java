package com.ankit.userservice.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    @Min(0)
    @Max(5)
    private int rating;
    private String feedback;
    private Hotel hotel;
}
