package com.shylu.spring_practice.repo;

import com.shylu.spring_practice.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUuid(String uuid);

    Optional<User> findByEmailOrMobile(String email, String mobile);

    Optional<User> findByEmailOrMobileAndPassword(String email, String mobile, String password);

    Optional<User> findByUsername(String username);
}
