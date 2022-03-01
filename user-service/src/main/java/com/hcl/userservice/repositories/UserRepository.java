package com.hcl.userservice.repositories;

import com.hcl.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query
    User findByName(String name);

    @Modifying
    @Transactional
    @Query
    void deleteByName(String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (name, password, email, admin, destination) VALUES (:name, :password, :email, :admin, :destination);", nativeQuery = true)
    void insertUser(@Param("name") String name, @Param("password") String password, @Param("email") String email, @Param("admin") boolean admin, @Param("destination") String destination);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET name=:name, password=:password, email=:email, admin=:admin, destination=:destination WHERE name=:name", nativeQuery = true)
    void updateUser(@Param("name") String name, @Param("password") String password, @Param("email") String email, @Param("admin") boolean admin, @Param("destination") String destination);
}
