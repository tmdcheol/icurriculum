package icurriculum.domain.member;

import icurriculum.domain.department.Department;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    private Integer joinYear;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Enumerated(STRING)
    private RoleType role;

    @Builder
    public Member(String name, Integer joinYear, Department department, RoleType role) {
        this.name = name;
        this.joinYear = joinYear;
        this.department = department;
        this.role = role;
    }
}
