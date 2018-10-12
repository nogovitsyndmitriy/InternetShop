package com.gmail.nogovitsyndmitriy.service.model;

import com.gmail.nogovitsyndmitriy.dao.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessCardDto {
    private Long id;
    private String title;
    private String fullName;
    private String workingTelephone;
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessCardDto)) return false;
        BusinessCardDto that = (BusinessCardDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(workingTelephone, that.workingTelephone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, fullName, workingTelephone);
    }

    @Override
    public String toString() {
        return "BusinessCardDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fullName='" + fullName + '\'' +
                ", workingTelephone='" + workingTelephone + '\'' +
                ", user=" + user +
                '}';
    }
}
