package icurriculum.domain.curriculum;

import icurriculum.domain.department.Department;
import icurriculum.domain.membermajor.MajorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

/**
 * Embedded type
 * 불변객체
 * 수정 X
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Embeddable
public class CurriculumDecider {

    @Enumerated(STRING)
    private MajorType majorType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    private Integer joinYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurriculumDecider that = (CurriculumDecider) o;
        return getMajorType() == that.getMajorType() && Objects.equals(getDepartment(), that.getDepartment()) && Objects.equals(getJoinYear(), that.getJoinYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMajorType(), getDepartment(), getJoinYear());
    }

}
