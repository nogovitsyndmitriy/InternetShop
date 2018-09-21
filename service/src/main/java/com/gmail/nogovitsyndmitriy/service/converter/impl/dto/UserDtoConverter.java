package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class UserDtoConverter implements DTOConverter<UserDto, User> {
    private ProfileDtoConverter profileDtoConverter = new ProfileDtoConverter();
    private AuditDtoConverter auditDtoConverter = new AuditDtoConverter();
    private NewsDtoConverter newsDtoConverter = new NewsDtoConverter();
    private RoleDtoConverter roleDtoConverter = new RoleDtoConverter();
    private DiscountDtoConverter discountDtoConverter = new DiscountDtoConverter();

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
        userDto.setDisabled(entity.getDisabled());
        // Add Profile
        if (entity.getProfile() != null) {
            ProfileDto profileDto = profileDtoConverter.toDTO(entity.getProfile());
            userDto.setProfileDto(profileDto);
        }
        //  Add Audit
        Set<AuditDto> auditDtoSet = new HashSet<>();
        for (Audit audits : entity.getAudits()) {
            auditDtoSet.add(auditDtoConverter.toDTO(audits));
        }
        userDto.setAuditDtoSet(auditDtoSet);
        //  Add News
        Set<NewsDto> newsDtoSet = new HashSet<>();
        for (News news : entity.getNews()) {
            newsDtoSet.add(newsDtoConverter.toDTO(news));
        }
        userDto.setNewsDtoSet(newsDtoSet);
        //  Role
        if (entity.getRole() != null) {
            RoleDto roleDto = roleDtoConverter.toDTO(entity.getRole());
            userDto.setRoleDto(roleDto);
        }
        //  Discount
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
