package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.PermissionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("permissionDtoConverter")
public class PermissionDtoConverter implements DTOConverter<PermissionDto, Permission> {
    @Override
    public PermissionDto toDTO(Permission entity) {
        if (entity == null) {
            return null;
        }
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(entity.getId());
        permissionDto.setName(entity.getName());
        return permissionDto;
    }

    @Override
    public List<PermissionDto> toDtoList(List<Permission> list) {
        return new ArrayList<>();
    }
}
