package entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "T_GENERIC_NEWS")
public class GenericNews implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "USER_ID")
    private Long userId;

}
