package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import service.converter.DTOConverter;
import service.model.PermissionDto;

import java.util.List;

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
        return null;
    }
}
