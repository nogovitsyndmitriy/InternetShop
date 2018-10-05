package com.gmail.nogovitsyndmitriy.service.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {

    private long id;
    private String title;
    private String content;
    private long userId;
    private LocalDateTime created;
    private UserDto user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsDto)) return false;
        NewsDto newsDto = (NewsDto) o;
        return id == newsDto.id &&
                userId == newsDto.userId &&
                Objects.equals(title, newsDto.title) &&
                Objects.equals(content, newsDto.content) &&
                Objects.equals(created, newsDto.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, content, userId, created);
    }
}
