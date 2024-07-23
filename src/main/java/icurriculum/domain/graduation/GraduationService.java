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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        Map<Category, List<Take>> takeMapForCategory = takeService.generateTakeMapForCategory(Member);

        // 3. 핵심교양SW창의_프로세스_진행
        핵심교양SW창의_프로세스_진행(response, 핵심교양_SW_창의_필요정보, takeMapForCategory);

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
        processorMap.get(ProcessorCategory.창의)
                .process(response, 핵심교양_SW_창의_필요정보, getTakes(takeMap, Category.창의));

        processorMap.get(ProcessorCategory.SW_AI)
                .process(response, 핵심교양_SW_창의_필요정보, getTakes(takeMap, Category.SW_AI));

        processorMap.get(ProcessorCategory.핵심교양)
                .process(response, 핵심교양_SW_창의_필요정보, getCoreTakes(takeMap));
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

    private void 학과별_교과과정_프로세스_진행() {

    }


}
