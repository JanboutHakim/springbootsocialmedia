package com.example.socialWeb.reoasitory;

import com.example.socialWeb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM socialweb.user where user_name=:userName ")
    User findByuser_name(@Param("userName") String username);

}
