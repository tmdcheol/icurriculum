package icurriculum.domain.graduation.processor.strategy.generalrequirement;

import icurriculum.domain.department.DepartmentName;
import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.graduation.processor.strategy.Processor;
import icurriculum.domain.graduation.processor.strategy.generalrequirement.strategy.GeneralRequirementStrategy;
import icurriculum.domain.take.Take;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 교양필수
 * ex. 프로네시스 세미나, 일반수학1 ...
 * 제외 -> 핵심교양, SW-AI, 창의
 */

@Component
@RequiredArgsConstructor
public class GeneralRequirementProcessor implements Processor<GeneralRequirement> {

    private final Map<DepartmentName, GeneralRequirementStrategy> generalRequirementStrategyMap;

    @Override
    public void process(GraduationResponse response, GeneralRequirement requirement, List<Take> takes) {
        Set<String> generalRequirementCode = requirement.getGeneralRequirementCode();
        DepartmentName departmentName = requirement.getDepartment().getName();

        generalRequirementStrategyMap.get(departmentName)
                .execute(response, generalRequirementCode, takes);
    }
}
