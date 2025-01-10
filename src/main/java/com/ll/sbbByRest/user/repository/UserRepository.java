package com.ll.sbbByRest.user.repository;

import com.ll.sbbByRest.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Integer> {

    Optional<SiteUser> findByUsername(String username);

}

