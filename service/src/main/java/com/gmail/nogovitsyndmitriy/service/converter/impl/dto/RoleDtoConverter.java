package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.PermissionDto;
import com.gmail.nogovitsyndmitriy.service.model.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("roleDtoConverter")
public class RoleDtoConverter implements DTOConverter<RoleDto, Role> {
    private final PermissionDtoConverter permissionDtoConverter;

    @Autowired
    public RoleDtoConverter(@Qualifier("permissionDtoConverter") PermissionDtoConverter permissionDtoConverter) {
        this.permissionDtoConverter = permissionDtoConverter;
    }

    @Override
    public RoleDto toDTO(Role entity) {
        if (entity == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());
//        Add Permissions
        Set<PermissionDto> permissionDtoSet = new HashSet<>();
        for (Permission permission : entity.getPermissions()) {
            PermissionDto permissionDto = permissionDtoConverter.toDTO(permission);
        }
        roleDto.setPermissionDtoSet(permissionDtoSet);

        return roleDto;
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> list) {
        return null;
    }
}
