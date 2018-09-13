package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import service.converter.DTOConverter;
import service.model.ProfileDto;

import java.util.List;

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
