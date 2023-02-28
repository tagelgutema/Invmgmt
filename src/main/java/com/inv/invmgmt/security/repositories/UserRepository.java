package com.inv.invmgmt.security.repositories;

import com.inv.invmgmt.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


  User findByUsername(String username);

}
