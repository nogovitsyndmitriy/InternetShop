package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import service.converter.DTOConverter;
import service.model.PermissionDto;
import service.model.RoleDto;
import service.model.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDtoConverter implements DTOConverter<RoleDto, Role> {
    private PermissionDtoConverter permissionDtoConverter = new PermissionDtoConverter();

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
