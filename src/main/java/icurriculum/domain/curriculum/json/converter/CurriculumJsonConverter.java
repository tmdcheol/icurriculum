package icurriculum.domain.curriculum.json.converter;

import icurriculum.domain.curriculum.json.CurriculumJson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 예외 추후 정의
 */
@Converter(autoApply = true)
public class CurriculumJsonConverter implements AttributeConverter<CurriculumJson, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(CurriculumJson attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public CurriculumJson convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, CurriculumJson.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
