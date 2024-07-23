package icurriculum.domain.graduation.processor;

import icurriculum.domain.curriculum.json.CoreJson;
import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.take.Take;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreativeProcessor implements Processor {
    public void process(GraduationResponse response, CoreJson 핵심교양_SW_창의_필요정보, List<Take> takes) {
        int 수강학점 = 0;
        int 기준학점 = 0;

        if (notRequiredCreative(핵심교양_SW_창의_필요정보)) {
            response.창의_영역_입력(수강학점, 기준학점);
            return;
        }

        수강학점 = calculateTotalCredit(takes);
        기준학점 = 핵심교양_SW_창의_필요정보.get창의();
        response.창의_영역_입력(수강학점, 기준학점);
    }

    private boolean notRequiredCreative(CoreJson 핵심교양_SW_창의_필요정보) {
        return 핵심교양_SW_창의_필요정보.get창의() == 0;
    }
}
