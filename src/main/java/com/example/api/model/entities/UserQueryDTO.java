package com.example.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserQueryDTO {
    private String username;
    private String avatar;
    private String rol;
}
