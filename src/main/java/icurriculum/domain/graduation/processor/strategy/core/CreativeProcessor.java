package icurriculum.domain.graduation.processor.strategy.core;

import icurriculum.domain.curriculum.json.CoreJson;
import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.graduation.processor.strategy.Processor;
import icurriculum.domain.take.Take;
import org.springframework.stereotype.Component;

import java.util.List;

import static icurriculum.domain.curriculum.json.CoreJson.*;

@Component
public class CreativeProcessor implements Processor<Creativity> {
    public void process(GraduationResponse response, Creativity 창의_필요정보, List<Take> takes) {
        int 이수학점 = calculateTotalCredit(takes);
        int 기준학점 = 창의_필요정보.get학점();
        response.창의_영역_입력(이수학점, 기준학점);
    }

}
