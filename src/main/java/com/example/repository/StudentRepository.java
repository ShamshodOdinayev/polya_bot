package com.example.repository;

import com.example.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<ProfileEntity, Integer> {

}
