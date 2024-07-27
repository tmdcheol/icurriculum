package icurriculum.domain.graduation.processor.strategy.generalrequirement.config;

import icurriculum.domain.department.DepartmentName;
import icurriculum.domain.graduation.processor.strategy.generalrequirement.strategy.GeneralRequirementStrategy;
import icurriculum.domain.graduation.processor.strategy.generalrequirement.strategy.AllRequiredStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static icurriculum.domain.department.DepartmentName.*;

@Configuration
@ComponentScan(basePackages = "icurriculum.domain.graduation.processor.strategy.generalrequirement.strategy")
public class GeneralRequirementStrategyConfig {

    @Bean
    public Map<DepartmentName, GeneralRequirementStrategy> generalRequirementStrategyMap(List<GeneralRequirementStrategy> strategies) {
        Map<DepartmentName, GeneralRequirementStrategy> strategyMap = new EnumMap<>(DepartmentName.class);

        for (GeneralRequirementStrategy strategy : strategies) {
            if (strategy instanceof AllRequiredStrategy) {
                strategyMap.put(컴퓨터공학과, strategy);
                // strategyMap.put(전기공학과, strategy);
            }

            /**
             * 학과별 구현 클래스 추가
             */
        }
        return strategyMap;
    }

}
