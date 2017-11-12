package com.michong.repositoty;


import com.michong.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepositoty extends JpaRepository<UserInfo, Long> {

    @Query("SELECT t FROM UserInfo t WHERE userName =: name")
    UserInfo findUserByName(@Param("name") String name);
}
