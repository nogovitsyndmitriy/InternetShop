package entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "T_GENERIC_COMMENT")
public class GenericComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "CREATED")
    private LocalDateTime created;
    @Column(name = "USER_ID")
    private long user_id;

    public GenericComment(String content, LocalDateTime created, long user_id) {
        this.content = content;
        this.created = created;
        this.user_id = user_id;
    }
}


