package icurriculum.domain.graduation.processor.strategy.core;

import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.graduation.processor.strategy.Processor;
import icurriculum.domain.take.Category;
import icurriculum.domain.take.Take;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static icurriculum.domain.curriculum.json.CoreJson.*;

@Component
public class CoreProcessor implements Processor<Core> {

    public void process(GraduationResponse response, Core 핵심교양_필요정보, List<Take> takes) {
        if (isAreaNominated(핵심교양_필요정보)) {
            executeWhenAreaNominated(response, 핵심교양_필요정보.get필수영역리스트(), takes);
            return;
        }
        executeWhenAreaNotNominated(response, 핵심교양_필요정보.get요구학점(), takes);
    }

    private boolean isAreaNominated(Core 핵심교양_필요정보) {
        return 핵심교양_필요정보.get영역지정여부();
    }

    private void executeWhenAreaNotNominated(GraduationResponse response, int 기준학점, List<Take> takes) {
        int 이수학점 = calculateTotalCredit(takes);
        response.핵심교양_영역_입력_영역상관없을때(이수학점, 기준학점);
    }

    private void executeWhenAreaNominated(GraduationResponse response, Set<Category> 필수영역, List<Take> takes) {
        Set<Category> 수강영역 = takes.stream()
                .map(Take::getCategory)
                .collect(Collectors.toSet());

        List<Category> 미이수영역 = new ArrayList<>();
        for (Category category : 필수영역) {
            if (!수강영역.contains(category)) {
                미이수영역.add(category);
            }
        }
        response.핵심교양_영역_입력_영역상관있을때(calculateTotalCredit(takes), 미이수영역);
    }
}