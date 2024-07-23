package icurriculum.domain.graduation.processor;

import icurriculum.domain.curriculum.json.CoreJson;
import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.take.Take;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwAiProcessor implements Processor {

    public void process(GraduationResponse response, CoreJson 핵심교양_SW_창의_필요정보, List<Take> takesList) {
        int 수강학점 = 0;
        int 기준학점 = 0;

        if (notRequiredSwAi(핵심교양_SW_창의_필요정보)) {
            response.SWAI_영역_입력(수강학점, 기준학점);
            return;
        }

        수강학점 = calculateTotalCredit(takesList);
        기준학점 = 핵심교양_SW_창의_필요정보.getSwAi();
        response.SWAI_영역_입력(수강학점, 기준학점);
    }

    private boolean notRequiredSwAi(CoreJson 핵심교양_SW_창의_필요정보) {
        return 핵심교양_SW_창의_필요정보.getSwAi() == 0;
    }
}
