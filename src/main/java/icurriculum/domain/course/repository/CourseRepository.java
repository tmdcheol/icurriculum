package icurriculum.domain.course.repository;

import icurriculum.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCode(String code);
}
