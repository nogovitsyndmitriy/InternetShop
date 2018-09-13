package service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import service.converter.Converter;
import service.model.ProfileDto;

import java.util.List;

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
        return null;
    }
}
