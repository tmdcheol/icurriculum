package icurriculum.domain.take.service;

import icurriculum.domain.member.Member;
import icurriculum.domain.take.Category;
import icurriculum.domain.take.Take;
import icurriculum.domain.take.repository.TakeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TakeService {
    private final TakeRepository repository;

    public Map<Category, List<Take>> generateTakeMapForCategory(Member member) {
        List<Take> takes = repository.findByMember(member);
        return takes.stream()
                .collect(Collectors.groupingBy(Take::getCategory));
    }

    public Map<String, Take> generateTakeMapForCalculate(Member member) {
        List<Take> takes = repository.findByMember(member);
        return takes.stream()
                .collect(Collectors.toMap(
                        Take::getCode,
                        take -> take,
                        this::handleDuplicateCode
                ));
    }

    /**
     * 과목 코드 중복 시 예외 발생
     */
    private Take handleDuplicateCode(Take existing, Take replacement) {

        /**
         * 예외 추후 정의
         */
        throw new RuntimeException();
    }
}
