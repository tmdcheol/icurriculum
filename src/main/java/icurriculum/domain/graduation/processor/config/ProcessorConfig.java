package icurriculum.domain.graduation.processor.config;

import icurriculum.domain.graduation.processor.strategy.Processor;
import icurriculum.domain.graduation.processor.strategy.core.CoreProcessor;
import icurriculum.domain.graduation.processor.strategy.core.CreativeProcessor;
import icurriculum.domain.graduation.processor.strategy.core.SwAiProcessor;
import icurriculum.domain.graduation.processor.strategy.generalrequirement.GeneralRequirementProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "icurriculum.domain.graduation.processor")
public class ProcessorConfig {

    @Bean
    public Map<ProcessorCategory, Processor<?>> processorMap(List<Processor<?>> processors) {
        Map<ProcessorCategory, Processor<?>> processorMap = new EnumMap<>(ProcessorCategory.class);
        for (Processor<?> processor : processors) {
            if (processor instanceof CreativeProcessor) {
                processorMap.put(ProcessorCategory.창의, processor);
                continue;
            }
            if (processor instanceof SwAiProcessor) {
                processorMap.put(ProcessorCategory.SW_AI, processor);
                continue;
            }
            if (processor instanceof CoreProcessor) {
                processorMap.put(ProcessorCategory.핵심교양, processor);
                continue;
            }
            if (processor instanceof GeneralRequirementProcessor) {
                processorMap.put(ProcessorCategory.교양필수, processor);
            }
        }
        return processorMap;
    }
}
