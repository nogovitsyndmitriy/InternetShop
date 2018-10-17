package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.ProfileDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("profileConverter")
public class ProfileConverter implements Converter<Profile, ProfileDto> {
    @Override
    public Profile toEntity(ProfileDto dto) {
        if (dto == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setUserId(dto.getUserId());
        profile.setAddress(dto.getAddress());
        profile.setTelephone(dto.getTelephone());

        return profile;
    }

    @Override
    public List<Profile> toEntityList(List<ProfileDto> list) {
        return new ArrayList<>();
    }
}
