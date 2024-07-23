package icurriculum.domain.curriculum.repository;

import icurriculum.domain.curriculum.Curriculum;
import icurriculum.domain.curriculum.decider.CurriculumDecider;

public interface CurriculumRepository {
    Curriculum findByCurriculumDecider(CurriculumDecider decider);

}
