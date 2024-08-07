package icurriculum.domain.take.service;

import icurriculum.domain.member.Member;
import icurriculum.domain.take.Category;
import icurriculum.domain.take.Take;
import icurriculum.domain.take.repository.TakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TakeService {
    private final TakeRepository repository;

    public Map<Category, List<Take>> getTakeMapForCategory(Member member) {
        List<Take> takes = repository.findByMember(member);
        return takes.stream()
                .collect(Collectors.groupingBy(Take::getCategory));
    }

}
