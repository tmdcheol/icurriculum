package icurriculum.domain.curriculum.repository;

import icurriculum.DataInitializer;
import icurriculum.domain.curriculum.Curriculum;
import icurriculum.domain.curriculum.CurriculumDecider;
import icurriculum.domain.department.Department;
import icurriculum.domain.department.repository.DepartmentRepository;
import icurriculum.domain.membermajor.MajorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static icurriculum.domain.membermajor.MajorType.주전공;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Transactional
class CurriculumRepositoryTest {

    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    DataInitializer dataInitializer;

    Long testDepartmentId = 1L;
    MajorType majorType;
    Department department;
    int joinYear;

    @BeforeEach
    void beforeEach() {
        majorType = 주전공;
        department = departmentRepository.findById(testDepartmentId).get();
        joinYear = 19;
    }

    @Test
    public void findByDecider() {
        // given
        CurriculumDecider decider = new CurriculumDecider(majorType, department, joinYear);
        Curriculum curriculumTestData = dataInitializer.getTestCurriculumData(department);

        // when
        Curriculum expected = curriculumRepository.findByDecider(decider)
                .orElseThrow(RuntimeException::new); // 예외 추후 정의

        // then
        assertThat(curriculumTestData.getDecider()).isEqualTo(expected.getDecider());
    }

}