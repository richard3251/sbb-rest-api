package com.ll.sbbByRest.user.service;

import com.ll.sbbByRest.exceptions.ServiceException;
import com.ll.sbbByRest.user.entity.SiteUser;
import com.ll.sbbByRest.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String password, String email) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);

        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new ServiceException("404-1", "회원 데이터가 없습니다.");
        }
    }


}
