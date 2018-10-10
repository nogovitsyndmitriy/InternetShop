package com.gmail.nogovitsyndmitriy.service.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private Long userId;
    private String content;
    private LocalDateTime created;
    private UserDto userDto;
    private NewsDto newsDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDto)) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, content, created);
    }
}
