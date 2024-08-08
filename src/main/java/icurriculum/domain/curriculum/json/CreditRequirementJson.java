package icurriculum.domain.curriculum.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CreditRequirementJson {

    /**
     *
     *   "필수이수학점": {
     *     "총이수": 130,
     *     "단일전공": 65,
     *     "복수연계융합전공": 39,
     *     "부전공": 21
     *   }
     *
     */

    @JsonProperty("총이수")
    private Integer 총이수;

    @JsonProperty("단일전공")
    private Integer 단일전공;

    @JsonProperty("복수연계융합전공")
    private Integer 복수연계융합전공;

    @JsonProperty("부전공")
    private Integer 부전공;

}
