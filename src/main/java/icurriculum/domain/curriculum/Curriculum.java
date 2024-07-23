package icurriculum.domain.curriculum;

import com.fasterxml.jackson.annotation.JsonProperty;
import icurriculum.domain.curriculum.decider.CurriculumDecider;
import icurriculum.domain.curriculum.json.CurriculumJson;
import icurriculum.domain.curriculum.json.CreditRequirementJson;
import icurriculum.domain.curriculum.json.CoreJson;
import lombok.Getter;

@Getter
public class Curriculum {

    private Long id;

    /**
     * 값 타입
     */
    private CurriculumDecider decider;

    @JsonProperty("핵심교양_SW_창의")
    private CoreJson CoreJson;

    @JsonProperty("필수이수학점")
    private CreditRequirementJson CreditRequirementJson;

    @JsonProperty("교과과정")
    private CurriculumJson CurriculumJson;

}
