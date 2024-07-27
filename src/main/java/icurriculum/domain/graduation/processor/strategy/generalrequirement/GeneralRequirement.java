package icurriculum.domain.graduation.processor.strategy.generalrequirement;

import icurriculum.domain.department.Department;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GeneralRequirement {

    /**
     * 교양필수_필수과목_코드
     * 학과
     */
    private final Set<String> generalRequirementCode;
    private final Department department;

    public static GeneralRequirement createGeneralRequirement(Set<String> generalRequirementCode, Department department) {
        return new GeneralRequirement(generalRequirementCode, department);
    }
}
