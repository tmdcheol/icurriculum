package icurriculum.domain.curriculum.decider;

import icurriculum.domain.department.Department;
import icurriculum.domain.membermajor.MajorType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Embedded type
 * 불변객체
 * 수정 X
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CurriculumDecider {

    private MajorType majorType;
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

    public static CurriculumDecider createDecider(MajorType majorType, Department department, Integer joinYear) {
        return new CurriculumDecider(majorType, department, joinYear);
    }

}
