package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.PermissionDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("permissionConverter")
public class PermissionConverter implements Converter<Permission, PermissionDto> {
    @Override
    public Permission toEntity(PermissionDto dto) {
        if (dto == null) {
            return null;
        }
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());

        return permission;
    }

    @Override
    public List<Permission> toEntityList(List<PermissionDto> list) {
        return null;
    }
}
