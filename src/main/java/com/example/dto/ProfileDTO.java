package com.example.dto;

import com.example.enums.ProfileRole;
import com.example.enums.Step;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileDTO {
    private Integer id;
    private String fullName;
    private String chatId;
    private String phone;
    private ProfileRole role;
    private Step step;
}
