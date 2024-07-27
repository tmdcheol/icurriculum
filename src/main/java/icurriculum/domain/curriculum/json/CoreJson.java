package icurriculum.domain.curriculum.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import icurriculum.domain.take.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * {
 *   "핵심교양_SW_창의": {
 *     "핵심교양": {
 *       "영역지정여부": true,
 *       "요구학점": 9,
 *       "필수영역리스트": ["핵심교양1", "핵심교양2", "핵심교양6"],
 *       "지정과목": {
 *         "핵심교양1": ["SUBJ1001", "SUBJ1002"],
 *         "핵심교양2": ["SUBJ2001", "SUBJ2002"],
 *         "핵심교양3": ["SUBJ3001", "SUBJ3002"],
 *         "핵심교양4": ["SUBJ4001", "SUBJ4002"],
 *         "핵심교양5": ["SUBJ5001", "SUBJ5002"],
 *         "핵심교양6": []
 *       }
 *     },
 *     "SW-AI": {
 *       "과목지정여부": true,
 *       "지정과목": ["ABC1234"],
 *       "학점": 3
 *     },
 *     "창의": {
 *       "과목지정여부": false,
 *       "지정과목": [],
 *       "학점": 2
 *     }
 *   }
 * }
 */

@Getter
@Setter
public class CoreJson {

    @JsonProperty("핵심교양")
    private Core 핵심교양;

    @JsonProperty("SW-AI")
    private SwAi swAi;

    @JsonProperty("창의")
    private Creativity 창의;

    @Getter
    @Setter
    public static class Core {

        @JsonProperty("영역지정여부")
        private Boolean 영역지정여부;

        @JsonProperty("요구학점")
        private Integer 요구학점;

        @JsonProperty("필수영역리스트")
        private Set<Category> 필수영역리스트;

        @JsonProperty("지정과목")
        private CoreSubjects 지정과목;

        @Getter
        @Setter
        public static class CoreSubjects {

            @JsonProperty("핵심교양1")
            private List<String> 핵심교양1;

            @JsonProperty("핵심교양2")
            private List<String> 핵심교양2;

            @JsonProperty("핵심교양3")
            private List<String> 핵심교양3;

            @JsonProperty("핵심교양4")
            private List<String> 핵심교양4;

            @JsonProperty("핵심교양5")
            private List<String> 핵심교양5;

            @JsonProperty("핵심교양6")
            private List<String> 핵심교양6;
        }
    }

    @Getter
    @Setter
    public static class SwAi {

        @JsonProperty("과목지정여부")
        private Boolean 과목지정여부;

        @JsonProperty("지정과목")
        private List<String> 지정과목;

        @JsonProperty("학점")
        private Integer 학점;
    }

    @Getter
    @Setter
    public static class Creativity {

        @JsonProperty("과목지정여부")
        private Boolean 과목지정여부;

        @JsonProperty("지정과목")
        private List<String> 지정과목;

        @JsonProperty("학점")
        private Integer 학점;
    }
}
