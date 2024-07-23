package icurriculum.domain.graduation.processor;

import icurriculum.domain.curriculum.json.CoreJson;
import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.take.Category;
import icurriculum.domain.take.Take;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoreProcessor implements Processor {
    public void process(GraduationResponse response, CoreJson 핵심교양_SW_창의_필요정보, List<List<Take>> takesList) {
        int 수강학점, 기준학점;
        if (영역상관없음(핵심교양_SW_창의_필요정보)) {
            수강학점 = calculateAllCredit(takesList);
            기준학점 = 핵심교양_SW_창의_필요정보.get핵심교양_요구학점();

            response.핵심교양_영역수정(수강학점, 기준학점);
            return;
        }

        List<Integer> 핵심교양_필수영역_리스트 = 핵심교양_SW_창의_필요정보.get핵심교양_필수영역_리스트();
        for (Integer area : 핵심교양_필수영역_리스트) {
            Category category = convertToCategory(area);

        }

    }

    private boolean 영역상관없음(CoreJson 핵심교양_SW_창의_필요정보) {
        return 핵심교양_SW_창의_필요정보.get영역상관없음();
    }

    private Category convertToCategory(int area) {
        if (area == 1) {
            return Category.핵심교양1;
        }
        if (area == 2) {
            return Category.핵심교양2;
        }
        if (area == 3) {
            return Category.핵심교양3;
        }
        if (area == 4) {
            return Category.핵심교양4;
        }
        if (area == 5) {
            return Category.핵심교양5;
        }
        return Category.핵심교양6;
    }

}