package icurriculum.domain.graduation.processor;

import icurriculum.domain.curriculum.json.CoreJson;
import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.take.Take;

import java.util.List;

public interface Processor {
    void process(GraduationResponse response, CoreJson 핵심교양_SW_창의_필요정보, List<List<Take>> takes);

    default int calculateOneCredit(List<Take> takes) {
        return takes.stream()
                .mapToInt(Take::getCredit)
                .sum();
    }

    default int calculateAllCredit(List<List<Take>> takesList) {
        return takesList.stream()
                .flatMap(List::stream)
                .mapToInt(Take::getCredit)
                .sum();
    }

}
