package icurriculum.domain.graduation;

import icurriculum.domain.curriculum.Curriculum;
import icurriculum.domain.curriculum.decider.CurriculumDecider;
import icurriculum.domain.curriculum.decider.DeciderUtils;
import icurriculum.domain.curriculum.service.CurriculumService;
import icurriculum.domain.department.Department;
import icurriculum.domain.graduation.processor.config.ProcessorCategory;
import icurriculum.domain.graduation.processor.strategy.Processor;
import icurriculum.domain.graduation.processor.strategy.generalrequirement.GeneralRequirement;
import icurriculum.domain.membermajor.service.MemberMajorService;
import icurriculum.domain.take.Take;
import icurriculum.domain.take.service.TakeService;
import icurriculum.domain.take.Category;
import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.member.Member;
import icurriculum.domain.curriculum.json.CoreJson;
import icurriculum.domain.membermajor.*;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static icurriculum.domain.graduation.processor.strategy.generalrequirement.GeneralRequirement.*;


/**
 * 졸업요건 확인 클래스
 */

@RequiredArgsConstructor
public class GraduationService {

    private final MemberMajorService memberMajorService;
    private final TakeService takeService;
    private final CurriculumService curriculumService;

    private final Map<ProcessorCategory, Processor<?>> processorMap;


    public void run(Member member) {
        GraduationResponse response = new GraduationResponse();

        // 1. 회원 전공 상태를 가져온다.
        List<MemberMajor> memberMajors = memberMajorService.getMemberMajors(member);

        // 2. 회원전공상태를 기반으로 핵심교양_SW_창의 관련 정보를 가져온다.
        Curriculum 주전공_curriculum = generate_주전공_curriculum(memberMajors);
        CoreJson 핵심교양_SW_창의_필요정보 = 주전공_curriculum.getCoreJson();

        // 3. 영역별 수강이력을 가져온다.
        Map<Category, List<Take>> takeMapForCategory = takeService.generateTakeMapForCategory(member);

        // 4. 핵심교양SW창의_프로세스_진행
        핵심교양SW창의_프로세스_진행(response, 핵심교양_SW_창의_필요정보, takeMapForCategory);

        // 5. 교양필수_프로세스_진행
        교양필수_프로세스_진행(response, 주전공_curriculum.getCurriculumJson().get교양필수(), member.getDepartment(), getTakes(takeMapForCategory, Category.교양필수));

        // 6. 전공 프로세스 진행
        전공_프로세스_진행();
    }

    /**
     * 회원 전공 상태를 기반으로 주전공 curriculum 가져오기
     */
    private Curriculum generate_주전공_curriculum(List<MemberMajor> memberMajors) {
        CurriculumDecider deciders = DeciderUtils.generateDeciderOnly주전공(memberMajors);
        return curriculumService.getByDecider(deciders);
    }

    private void 핵심교양SW창의_프로세스_진행(GraduationResponse response, CoreJson 핵심교양_SW_창의_필요정보, Map<Category, List<Take>> takeMap) {
        getProcessor(ProcessorCategory.창의)
                .process(response, 핵심교양_SW_창의_필요정보.get창의(), getTakes(takeMap, Category.창의));

        getProcessor(ProcessorCategory.SW_AI)
                .process(response, 핵심교양_SW_창의_필요정보.getSwAi(), getTakes(takeMap, Category.SW_AI));

        getProcessor(ProcessorCategory.핵심교양)
                .process(response, 핵심교양_SW_창의_필요정보.get핵심교양(), getCoreTakes(takeMap));
    }

    private List<Take> getTakes(Map<Category, List<Take>> takeMap, Category category) {
        return Optional.ofNullable(takeMap.get(category)).orElse(Collections.emptyList());
    }

    private List<Take> getCoreTakes(Map<Category, List<Take>> takeMap) {
        return Stream.of(
                        getTakes(takeMap, Category.핵심교양1),
                        getTakes(takeMap, Category.핵심교양2),
                        getTakes(takeMap, Category.핵심교양3),
                        getTakes(takeMap, Category.핵심교양4),
                        getTakes(takeMap, Category.핵심교양5),
                        getTakes(takeMap, Category.핵심교양6)
                )
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private void 교양필수_프로세스_진행(GraduationResponse response, Set<String> generalRequirementCode, Department department, List<Take> takes) {
        GeneralRequirement generalRequirement = createGeneralRequirement(generalRequirementCode, department);
        getProcessor(ProcessorCategory.교양필수)
                .process(response, generalRequirement, takes);
    }

    private <T> Processor<T> getProcessor(ProcessorCategory category) {
        Processor<?> processor = processorMap.get(category);
        if (processor == null) {
            /**
             * 예외 추후 정의
             */
            throw new RuntimeException();
        }
        return (Processor<T>) processor;
    }


    private void 전공_프로세스_진행() {

    }


}
