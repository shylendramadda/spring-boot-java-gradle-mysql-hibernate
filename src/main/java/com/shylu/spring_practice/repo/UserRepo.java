package com.shylu.spring_practice.repo;

import com.shylu.spring_practice.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByUuid(String uuid);
    User findByEmailOrMobile(String email, String mobile);
    User findByEmailOrMobileAndPassword(String email, String mobile, String password);
}
