package com.michong.repositoty;


import com.michong.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepositoty extends JpaRepository<UserInfo, Long> {

    @Query("SELECT t FROM UserInfo t WHERE userName =: name")
    List<UserInfo> findUserByName(@Param("name") String name);
}
