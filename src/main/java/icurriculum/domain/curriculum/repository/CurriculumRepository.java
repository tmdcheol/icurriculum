package icurriculum.domain.curriculum.repository;

import icurriculum.domain.curriculum.Curriculum;
import icurriculum.domain.curriculum.CurriculumDecider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    @Query("select c from Curriculum c join fetch c.decider.department d where c.decider = :decider")
    Optional<Curriculum> findByDecider(@Param("decider") CurriculumDecider decider);

}
