package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.ProfileDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProfileDtoConverter implements DTOConverter<ProfileDto, Profile> {
    @Override
    public ProfileDto toDTO(Profile entity) {
        if (entity == null) {
            return null;
        }
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUserId(entity.getUserId());
        profileDto.setAddress(entity.getAddress());
        profileDto.setTelephone(entity.getTelephone());
        return profileDto;
    }

    @Override
    public List<ProfileDto> toDtoList(List<Profile> list) {
        return null;
    }
}
