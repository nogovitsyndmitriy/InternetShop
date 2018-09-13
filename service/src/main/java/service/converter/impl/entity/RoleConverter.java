package service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import service.converter.Converter;
import service.model.PermissionDto;
import service.model.RoleDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleConverter implements Converter<Role, RoleDto> {
    @Override
    public Role toEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        //  Permissions
        PermissionConverter permissionConverter = new PermissionConverter();
        Set<Permission> permissions = new HashSet<>();
        for (PermissionDto permissionDto : dto.getPermissionDtoSet()) {
            permissions.add(permissionConverter.toEntity(permissionDto));
        }
        role.setPermissions(permissions);
        for (Permission permission : permissions) {
            permission.getRoles().add(role);
        }

        return role;
    }

    @Override
    public List<Role> toEntityList(List<RoleDto> list) {
        return null;
    }
}
