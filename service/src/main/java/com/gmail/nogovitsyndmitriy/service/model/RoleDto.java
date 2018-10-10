package com.gmail.nogovitsyndmitriy.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private Set<PermissionDto> permissionDtoSet = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDto)) return false;
        RoleDto roleDto = (RoleDto) o;
        return id.equals(roleDto.id) &&
                Objects.equals(name, roleDto.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
