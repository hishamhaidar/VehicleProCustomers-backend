package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDTO {
    private Integer userID;
    private String firstName;
    private String lastName;

}
