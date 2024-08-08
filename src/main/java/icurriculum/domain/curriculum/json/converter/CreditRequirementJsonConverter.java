package icurriculum.domain.curriculum.json.converter;


import icurriculum.domain.curriculum.json.CreditRequirementJson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 예외 추후 정의
 */
@Converter(autoApply = true)
public class CreditRequirementJsonConverter implements AttributeConverter<CreditRequirementJson, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(CreditRequirementJson attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public CreditRequirementJson convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, CreditRequirementJson.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
