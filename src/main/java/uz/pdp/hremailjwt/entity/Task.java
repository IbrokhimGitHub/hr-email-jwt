package uz.pdp.hremailjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import uz.pdp.hremailjwt.entity.enums.TaskStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(nullable = false)
    private String taskName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Timestamp deadline;

    @ManyToOne
    private Employee responsibleEmployee;  //vazifaga javobgar xodim

    @CreatedBy
    private UUID createdEmployee; //vazifa qo'shgan xodim


}
