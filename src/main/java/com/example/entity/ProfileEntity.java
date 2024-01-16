package com.example.entity;

import com.example.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "chatId")
    private String chatId;
    @Column(name = "phone")
    private String phone;
    @Column(name = "role")
    private ProfileRole role;

}
