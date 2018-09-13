package service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import service.converter.Converter;
import service.model.PermissionDto;

import java.util.List;

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
