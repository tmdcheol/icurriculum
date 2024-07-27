package icurriculum.domain.graduation.processor.strategy.generalrequirement.strategy;

import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.take.Take;

import java.util.List;
import java.util.Set;

public interface GeneralRequirementStrategy {
    void execute(GraduationResponse response, Set<String> generalRequirementCode, List<Take> takes);

    default int calculateTotalCredit(List<Take> takesList) {
        return takesList.stream()
                .mapToInt(Take::getCredit)
                .sum();
    }
}
