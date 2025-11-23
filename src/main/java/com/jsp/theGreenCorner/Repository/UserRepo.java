package com.jsp.theGreenCorner.Repository;

import com.jsp.theGreenCorner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    @Query("select u from User u where u.email =:email and u.password =:pass")
    public User findEmailPass(String email,String pass);
}
