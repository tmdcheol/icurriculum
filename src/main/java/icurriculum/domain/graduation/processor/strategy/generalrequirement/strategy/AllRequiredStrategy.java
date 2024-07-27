package icurriculum.domain.graduation.processor.strategy.generalrequirement.strategy;

import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.take.Take;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 필수과목 모두 수강하는 학과
 */
@Component
public class AllRequiredStrategy implements GeneralRequirementStrategy {
    @Override
    public void execute(GraduationResponse response, Set<String> generalRequirementCode, List<Take> takes) {
        int 이수학점 = calculateTotalCredit(takes);

        Set<String> 수강과목코드 = takes.stream()
                .map(Take::getCode)
                .collect(Collectors.toSet());

        List<String> 미이수_과목 = new ArrayList<>();

        for (String code : generalRequirementCode) {
            if (!수강과목코드.contains(code)) {
                미이수_과목.add(code);
            }
        }
        response.교양필수_영역_입력(이수학점, );
        response.핵심교양_영역_입력_영역상관있을때(calculateTotalCredit(takes), 미이수영역);
        response.창의_영역_입력(수강학점, 기준학점);
    }
}
