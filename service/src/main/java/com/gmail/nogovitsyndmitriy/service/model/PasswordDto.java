package com.gmail.nogovitsyndmitriy.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class PasswordDto {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordDto)) return false;
        PasswordDto that = (PasswordDto) o;
        return Objects.equals(currentPassword, that.currentPassword) &&
                Objects.equals(newPassword, that.newPassword) &&
                Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(currentPassword, newPassword, confirmPassword);
    }
}
