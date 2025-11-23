package com.elevata.gsrtc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepotAdminLoginDTO {
    private String email;
    private String password;
}
