package entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_GENERIC_ROLE_PERMISSION")
public class GenericRolePermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID", nullable = false, updatable = false)
    private long role_id;
    @Column(name = "PERMISSION_ID")
    private long permission_id;
}
