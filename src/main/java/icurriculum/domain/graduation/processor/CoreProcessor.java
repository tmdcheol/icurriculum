package icurriculum.domain.graduation.processor;

import icurriculum.domain.curriculum.json.CoreJson;
import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.take.Category;
import icurriculum.domain.take.Take;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CoreProcessor implements Processor {

    public void process(GraduationResponse response, CoreJson 핵심교양_SW_창의_필요정보, List<Take> takes) {
        if (영역상관없음(핵심교양_SW_창의_필요정보)) {
            execute_영역상관없음(response, 핵심교양_SW_창의_필요정보, takes);
            return;
        }
        execute_영역상관있음(response, 핵심교양_SW_창의_필요정보.get핵심교양_필수영역_리스트(), takes);
    }

    private boolean 영역상관없음(CoreJson 핵심교양_SW_창의_필요정보) {
        return 핵심교양_SW_창의_필요정보.get영역상관없음();
    }

    private void execute_영역상관없음(GraduationResponse response, CoreJson 핵심교양_SW_창의_필요정보, List<Take> takes) {
        int 수강학점, 기준학점;
        수강학점 = calculateTotalCredit(takes);
        기준학점 = 핵심교양_SW_창의_필요정보.get핵심교양_요구학점();
        response.핵심교양_영역_입력_영역상관없을때(수강학점, 기준학점);
    }

    private void execute_영역상관있음(GraduationResponse response, List<Category> 핵심교양_필수영역_리스트, List<Take> takes) {
        Set<Category> 수강영역 = takes.stream()
                .map(Take::getCategory)
                .collect(Collectors.toSet());

        List<Category> 미이수영역 = new ArrayList<>();
        for (Category category : 핵심교양_필수영역_리스트) {
            if (!수강영역.contains(category)) {
                미이수영역.add(category);
            }
        }
        response.핵심교양_영역_입력_영역상관있을때(calculateTotalCredit(takes), 미이수영역);
    }
}