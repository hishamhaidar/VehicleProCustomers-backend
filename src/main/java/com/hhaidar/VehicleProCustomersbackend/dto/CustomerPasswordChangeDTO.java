package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerPasswordChangeDTO {
    private String currentPassword;
    private String newPassword;
}
