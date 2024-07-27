package icurriculum.domain.graduation.processor.strategy;

import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.take.Take;

import java.util.List;

public interface Processor<T> {
    void process(GraduationResponse response, T requirement, List<Take> takes);

    default int calculateTotalCredit(List<Take> takesList) {
        return takesList.stream()
                .mapToInt(Take::getCredit)
                .sum();
    }

}
