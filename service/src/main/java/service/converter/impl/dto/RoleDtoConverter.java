package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import service.converter.DTOConverter;
import service.model.RoleDto;
import service.model.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDtoConverter implements DTOConverter<RoleDto, Role> {
    @Override
    public RoleDto toDTO(Role entity) {
        if (entity == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());
        //  Add Users
        Set<UserDto> userDtoSet = new HashSet<>();


        return roleDto;
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> list) {
        return null;
    }
}
