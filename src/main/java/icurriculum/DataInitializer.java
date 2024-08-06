package icurriculum;

import icurriculum.domain.department.Department;
import icurriculum.domain.department.DepartmentName;
import icurriculum.domain.department.repository.DepartmentRepository;
import icurriculum.domain.member.Member;
import icurriculum.domain.member.RoleType;
import icurriculum.domain.member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final MemberRepository memberRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * 기본 데이터 추가
     */
    @PostConstruct
    public void init() {
        // 학과
        Department department = Department.builder()
                .name(DepartmentName.컴퓨터공학과)
                .build();
        departmentRepository.save(department);
        
        // 회원
        Member member = Member.builder()
                .name("이승철")
                .joinYear(19)
                .department(department)
                .role(RoleType.ROLE_USER)
                .build();
        memberRepository.save(member);
    }
}
