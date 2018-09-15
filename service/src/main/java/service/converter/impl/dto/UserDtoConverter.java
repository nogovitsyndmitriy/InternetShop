package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import service.converter.DTOConverter;
import service.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDtoConverter implements DTOConverter<UserDto, User> {
    @Override
    public UserDto toDTO(User entity) {
        if (entity == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setName(entity.getName());
        userDto.setSurname(entity.getSurname());
        userDto.setPassword(entity.getPassword());
        // Add Profile
        ProfileDtoConverter profileDtoConverter = new ProfileDtoConverter();
        if (entity.getProfile() != null) {
            ProfileDto profileDto = profileDtoConverter.toDTO(entity.getProfile());
            userDto.setProfileDto(profileDto);
        }
        //  Add Audit
        AuditDtoConverter auditDtoConverter = new AuditDtoConverter();
        Set<AuditDto> auditDtoSet = new HashSet<>();
        for (Audit audits : entity.getAudits()) {
            auditDtoSet.add(auditDtoConverter.toDTO(audits));
        }
        userDto.setAuditDtoSet(auditDtoSet);
        //  Add News
        NewsDtoConverter newsDtoConverter = new NewsDtoConverter();
        Set<NewsDto> newsDtoSet = new HashSet<>();
        for (News news : entity.getNews()) {
            newsDtoSet.add(newsDtoConverter.toDTO(news));
        }
        userDto.setNewsDtoSet(newsDtoSet);
        //  Role
        RoleDtoConverter roleDtoConverter = new RoleDtoConverter();
        if (entity.getRole() != null) {
            RoleDto roleDto = roleDtoConverter.toDTO(entity.getRole());
            userDto.setRoleDto(roleDto);
        }
        //  Discount
        DiscountDtoConverter discountDtoConverter = new DiscountDtoConverter();
        if (entity.getDiscount() != null) {
            DiscountDto discountDto = discountDtoConverter.toDTO(entity.getDiscount());
            userDto.setDiscountDto(discountDto);
        }
        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<User> list) {
        return null;
    }
}
