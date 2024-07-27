package icurriculum.domain.department;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class Department {

    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "department_name")
    private DepartmentName name;

}
