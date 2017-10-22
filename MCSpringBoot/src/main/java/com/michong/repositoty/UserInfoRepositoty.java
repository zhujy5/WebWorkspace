package com.michong.repositoty;


import com.michong.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepositoty extends JpaRepository<UserInfo, Long> {

}
