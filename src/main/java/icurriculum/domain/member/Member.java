package icurriculum.domain.member;

import icurriculum.domain.department.Department;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class Member {

    private Long id;

    @Column(name = "member_name")
    private String name;

    private Integer joinYear;

    private Department Department;

    private RoleType role;

}
