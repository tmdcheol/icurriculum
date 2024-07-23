package icurriculum.domain.department;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class Department {

    private Long id;

    @Column(name = "department_name")
    private String name;

}
