package com.ll.sbbByRest.user.controller;

import com.ll.sbbByRest.exceptions.ServiceException;
import com.ll.sbbByRest.rs.RsData;
import com.ll.sbbByRest.user.dto.SiteUserDto;
import com.ll.sbbByRest.user.entity.SiteUser;
import com.ll.sbbByRest.user.form.UserCreateForm;
import com.ll.sbbByRest.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public RsData<SiteUserDto> signup(@RequestBody @Valid UserCreateForm userCreateForm) {

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            throw new ServiceException("400-1", "2개의 패스워드가 일치하지 않습니다.");
        }

        SiteUser siteUser =  this.userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(), userCreateForm.getEmail());

        return new RsData<>(
                "200-1",
                "환영합니다.",
                new SiteUserDto(siteUser)
        );
    }





}
