package com.ll.sbbByRest.user.dto;

import com.ll.sbbByRest.user.entity.SiteUser;
import lombok.Getter;

@Getter
public class SiteUserDto {

    private Integer id;

    private String username;

    private String email;

    public SiteUserDto(SiteUser siteUser) {
        this.id = siteUser.getId();
        this.username = siteUser.getUsername();
        this.email = siteUser.getEmail();
    }

}
