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
public class UserDto {
    private long id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private Boolean disabled;
    private ProfileDto profileDto;
    private Set<NewsDto> newsDtoSet = new HashSet<>();
    private Set<AuditDto> auditDtoSet = new HashSet<>();
    private RoleDto roleDto;
    private DiscountDto discountDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto userDto = (UserDto) o;
        return id == userDto.id &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(surname, userDto.surname) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(disabled, userDto.disabled);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, name, surname, password, disabled);
    }
}
