package icurriculum.domain.curriculum.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CurriculumJson {

    /**
     * "교과과정": {
     * "교양필수_리스트": ["GED1234", "GEE1234"],
     * "전공필수_리스트": ["CSE1231", "CSE1234"],
     * "전공선택_리스트": ["CSE1231", "CSE1234"]
     * }
     */

    @JsonProperty("교양필수_리스트")
    private Set<String> 교양필수;

    @JsonProperty("전공필수_리스트")
    private Set<String> 전공필수;

    @JsonProperty("전공선택_리스트")
    private Set<String> 전공선택;

}
