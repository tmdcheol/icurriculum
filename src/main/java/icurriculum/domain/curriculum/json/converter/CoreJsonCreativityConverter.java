package icurriculum.domain.curriculum.json.converter;

import icurriculum.domain.curriculum.json.CoreJson.Creativity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 예외 추후 정의
 */
@Converter
public class CoreJsonCreativityConverter implements AttributeConverter<Creativity, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Creativity attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting Creativity to JSON", e);
        }
    }

    @Override
    public Creativity convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Creativity.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON to Creativity", e);
        }
    }
}
