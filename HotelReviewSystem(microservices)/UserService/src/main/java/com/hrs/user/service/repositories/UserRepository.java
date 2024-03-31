package com.hrs.user.service.repositories;

import com.hrs.user.service.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    // any custom database logic goes here

}
