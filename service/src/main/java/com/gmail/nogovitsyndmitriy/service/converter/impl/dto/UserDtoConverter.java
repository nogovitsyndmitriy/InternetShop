package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("userDtoConverter")
public class UserDtoConverter implements DTOConverter<UserDto, User> {
    private final ProfileDtoConverter profileDtoConverter;
    private final AuditDtoConverter auditDtoConverter;
    private final RoleDtoConverter roleDtoConverter;
    private final DiscountDtoConverter discountDtoConverter;

    @Autowired
    public UserDtoConverter(@Qualifier("profileDtoConverter") ProfileDtoConverter profileDtoConverter, @Qualifier("auditDtoConverter") AuditDtoConverter auditDtoConverter, @Qualifier("roleDtoConverter") RoleDtoConverter roleDtoConverter, @Qualifier("discountDtoConverter") DiscountDtoConverter discountDtoConverter) {
        this.profileDtoConverter = profileDtoConverter;
        this.auditDtoConverter = auditDtoConverter;
        this.roleDtoConverter = roleDtoConverter;
        this.discountDtoConverter = discountDtoConverter;
    }

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
        List<UserDto> users = new ArrayList<>();
        for (User user : list) {
            users.add(toDTO(user));
        }
        return users;
    }
}
