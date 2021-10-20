package uz.pdp.hremailjwt.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hremailjwt.entity.Employee;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {

    private String taskName;


    private String description;


    private Timestamp deadline;

  @Email
    private String responsibleEmployeeEmail;
  private  String taskCode;
}
