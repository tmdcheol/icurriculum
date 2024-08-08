package icurriculum.domain.curriculum.json.converter;

import icurriculum.domain.curriculum.json.CoreJson.SwAi;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 예외 추후 정의
 */
@Converter
public class CoreJsonSwAiConverter implements AttributeConverter<SwAi, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(SwAi attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public SwAi convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, SwAi.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
