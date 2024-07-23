package icurriculum.domain.curriculum.service;

import icurriculum.domain.curriculum.Curriculum;
import icurriculum.domain.curriculum.decider.CurriculumDecider;
import icurriculum.domain.curriculum.repository.CurriculumRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurriculumService {

    private final CurriculumRepository repository;

    public Curriculum getByDecider(CurriculumDecider decider){
        return repository.findByCurriculumDecider(decider);
    }

}
