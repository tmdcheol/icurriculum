package icurriculum.domain.curriculum;

import com.fasterxml.jackson.annotation.JsonProperty;
import icurriculum.domain.curriculum.json.CurriculumJson;
import icurriculum.domain.curriculum.json.CreditRequirementJson;
import icurriculum.domain.curriculum.json.CoreJson;
import icurriculum.domain.curriculum.json.converter.CoreJsonConverter;
import icurriculum.domain.curriculum.json.converter.CreditRequirementJsonConverter;
import icurriculum.domain.curriculum.json.converter.CurriculumJsonConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Curriculum {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "curriculum_id")
    private Long id;

    @Embedded
    private CurriculumDecider decider;


    @Convert(converter = CoreJsonConverter.class)
    @JsonProperty("핵심교양_SW_창의")
    private CoreJson coreJson;

    @Convert(converter = CreditRequirementJsonConverter.class)
    @JsonProperty("필수이수학점")
    private CreditRequirementJson creditRequirementJson;

    @Convert(converter = CurriculumJsonConverter.class)
    @JsonProperty("교과과정")
    private CurriculumJson curriculumJson;

    @Builder
    public Curriculum(CurriculumDecider decider, CoreJson coreJson, CreditRequirementJson creditRequirementJson, CurriculumJson curriculumJson) {
        this.decider = decider;
        this.coreJson = coreJson;
        this.creditRequirementJson = creditRequirementJson;
        this.curriculumJson = curriculumJson;
    }
}
