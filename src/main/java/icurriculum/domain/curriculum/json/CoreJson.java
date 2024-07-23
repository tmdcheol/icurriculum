package icurriculum.domain.curriculum.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoreJson {

    /**
     *
     *     "핵심교양_SW_창의": {
     *         "영역상관없음": true,
     *         "핵심교양_요구학점" : null,
     *         "핵심교양_필수영역": [1, 4, 6],
     *         "SW-AI": 3,
     *         "창의": 2
     *     }
     */

    @JsonProperty("영역상관없음")
    private Boolean 영역상관없음;

    @JsonProperty("핵심교양_요구학점")
    private Integer 핵심교양_요구학점;

    @JsonProperty("핵심교양_필수영역")
    private List<Integer> 핵심교양_필수영역_리스트;

    @JsonProperty("SW-AI")
    private Integer swAi;

    @JsonProperty("창의")
    private Integer 창의;

}
