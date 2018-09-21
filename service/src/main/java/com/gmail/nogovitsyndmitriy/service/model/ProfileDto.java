package com.gmail.nogovitsyndmitriy.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private long userId;
    private String address;
    private String telephone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileDto)) return false;
        ProfileDto that = (ProfileDto) o;
        return userId == that.userId &&
                Objects.equals(address, that.address) &&
                Objects.equals(telephone, that.telephone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, address, telephone);
    }
}
