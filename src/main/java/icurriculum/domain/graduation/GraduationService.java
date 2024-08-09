package icurriculum.domain.graduation;

import icurriculum.domain.curriculum.Curriculum;
import icurriculum.domain.curriculum.CurriculumDecider;
import icurriculum.domain.curriculum.repository.CurriculumRepository;
import icurriculum.domain.curriculum.util.MajorToDeciderConverter;
import icurriculum.domain.department.Department;
import icurriculum.domain.graduation.processor.config.ProcessorCategory;
import icurriculum.domain.graduation.processor.strategy.Processor;
import icurriculum.domain.graduation.processor.strategy.generalrequirement.GeneralRequirement;
import icurriculum.domain.membermajor.repository.MemberMajorRepository;
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
import static icurriculum.domain.membermajor.util.MemberMajorUtils.getMainMemberMajor;


/**
 * 졸업요건 확인 클래스
 */

@RequiredArgsConstructor
public class GraduationService {

    private final TakeService takeService;
    private final MemberMajorRepository memberMajorRepository;
    private final CurriculumRepository curriculumRepository;

    private final Map<ProcessorCategory, Processor<?>> processorMap;


    public void check(Member member) {
        GraduationResponse response = new GraduationResponse();

        // 1. 영역별 수강내역을 가져온다.
        Map<Category, List<Take>> takeMapForCategory = takeService.getTakeMapForCategory(member);

        // 2. 회원 전공 상태 List 가져온다.
        List<MemberMajor> memberMajors = memberMajorRepository.findByMember(member);

        // 3. 회원전공상태 중 주전공을 기반으로 교양 관련 정보들을 가져온다.
        MemberMajor mainMemberMajor = getMainMemberMajor(memberMajors);
        CurriculumDecider mainCurriculumDecider = MajorToDeciderConverter.of(mainMemberMajor);
        Curriculum 주전공_curriculum = curriculumRepository.findByDecider(mainCurriculumDecider)
                .orElseThrow(RuntimeException::new); // 예외 추후 정의


        CoreJson 핵심교양_SW_창의_필요정보 = 주전공_curriculum.getCoreJson();
        Set<String> 교양필수_필수과목 = 주전공_curriculum.getCurriculumJson().get교양필수();

        // 4. 핵심교양SW창의_프로세스_진행
        핵심교양SW창의_프로세스_진행(response, 핵심교양_SW_창의_필요정보, takeMapForCategory);

        // 5. 교양필수_프로세스_진행
        교양필수_프로세스_진행(response,교양필수_필수과목, 주전공_curriculum.getDecider().getDepartment(), getTakes(takeMapForCategory, Category.교양필수));

        // 6. 전공 프로세스 진행
        전공_프로세스_진행();
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
