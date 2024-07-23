package icurriculum.domain.graduation;

import icurriculum.domain.curriculum.decider.CurriculumDecider;
import icurriculum.domain.curriculum.decider.DeciderUtils;
import icurriculum.domain.curriculum.service.CurriculumService;
import icurriculum.domain.graduation.config.ProcessorCategory;
import icurriculum.domain.graduation.processor.Processor;
import icurriculum.domain.membermajor.service.MemberMajorService;
import icurriculum.domain.take.Take;
import icurriculum.domain.take.service.TakeService;
import icurriculum.domain.take.Category;
import icurriculum.domain.graduation.dto.GraduationResponse;
import icurriculum.domain.member.Member;
import icurriculum.domain.curriculum.json.CoreJson;
import icurriculum.domain.membermajor.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;


/**
 * 졸업요건 확인 클래스
 */

@RequiredArgsConstructor
public class GraduationService {

    private final MemberMajorService memberMajorService;
    private final TakeService takeService;
    private final CurriculumService curriculumService;

    private final Map<ProcessorCategory, Processor> processorMap;


    public void run(Member Member) {
        GraduationResponse response = new GraduationResponse();

        // 1. 회원 전공 상태를 가져온다.
        List<MemberMajor> memberMajors = memberMajorService.getMemberMajors(Member);

        // 2. 회원전공상태를 기반으로 핵심교양_SW_창의 관련 정보를 가져온다.
        CoreJson 핵심교양_SW_창의_필요정보 = generate_핵심교양_SW_창의(memberMajors);

        // 3. 수강이력을 가져온다.
        Map<Category, List<Take>> takeMap = takeService.generateTakeMapForCategory(Member);

        // 3. 핵심교양SW창의_프로세스_진행
        핵심교양SW창의_프로세스_진행(response, 핵심교양_SW_창의_필요정보, takeMap);

        // 4. 학과별_교과과정_프로세스_진행
        학과별_교과과정_프로세스_진행();
    }

    /**
     * 회원 전공 상태를 기반으로 주전공 curriculum 가져오기
     */
    private CoreJson generate_핵심교양_SW_창의(List<MemberMajor> memberMajors) {
        CurriculumDecider deciders = DeciderUtils.generateDeciderOnly주전공(memberMajors);
        return curriculumService.getByDecider(deciders)
                .getCoreJson();
    }

    private void 핵심교양SW창의_프로세스_진행(GraduationResponse response, CoreJson 핵심교양_SW_창의_필요정보, Map<Category, List<Take>> takeMap) {
        getProcessor(processorMap, ProcessorCategory.창의)
                .process(response, 핵심교양_SW_창의_필요정보, List.of(getTakes(takeMap, Category.창의)));

        getProcessor(processorMap, ProcessorCategory.SW_AI)
                .process(response, 핵심교양_SW_창의_필요정보, List.of(getTakes(takeMap, Category.SW_AI)));

        getProcessor(processorMap, ProcessorCategory.핵심교양)
                .process(response, 핵심교양_SW_창의_필요정보,
                        List.of(
                                getTakes(takeMap, Category.핵심교양1),
                                getTakes(takeMap, Category.핵심교양2),
                                getTakes(takeMap, Category.핵심교양3),
                                getTakes(takeMap, Category.핵심교양4),
                                getTakes(takeMap, Category.핵심교양5),
                                getTakes(takeMap, Category.핵심교양6)
                        ));

    }

    private Processor getProcessor(Map<ProcessorCategory, Processor> processorMap, ProcessorCategory category) {
        Processor processor = processorMap.get(category);
        /**
         * 예외 추후 정의
         */
        if (processor == null) {
            throw new RuntimeException();
        }
        return processor;
    }

    private List<Take> getTakes(Map<Category, List<Take>> takeMap, Category category) {
        List<Take> takes = takeMap.get(category);
        /**
         * 예외 추후 정의
         */
        if (takes == null) {
            throw new RuntimeException();
        }
        return takes;
    }

    private void 학과별_교과과정_프로세스_진행() {

    }


}
