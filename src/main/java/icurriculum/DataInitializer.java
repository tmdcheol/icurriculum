package icurriculum;

import icurriculum.domain.course.Course;
import icurriculum.domain.course.repository.CourseRepository;
import icurriculum.domain.department.Department;
import icurriculum.domain.department.DepartmentName;
import icurriculum.domain.department.repository.DepartmentRepository;
import icurriculum.domain.member.Member;
import icurriculum.domain.member.RoleType;
import icurriculum.domain.member.repository.MemberRepository;
import icurriculum.domain.membermajor.MajorType;
import icurriculum.domain.membermajor.MemberMajor;
import icurriculum.domain.membermajor.repository.MemberMajorRepository;
import icurriculum.domain.take.Category;
import icurriculum.domain.take.Take;
import icurriculum.domain.take.repository.TakeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final MemberRepository memberRepository;
    private final DepartmentRepository departmentRepository;
    private final MemberMajorRepository memberMajorRepository;

    private final CourseRepository courseRepository;
    private final TakeRepository takeRepository;

    /**
     * 기본 데이터 추가
     */
    @PostConstruct
    public void init() {

        // department
        Department department = Department.builder()
                .name(DepartmentName.컴퓨터공학과)
                .build();
        departmentRepository.save(department);

        // member
        Member member = Member.builder()
                .name("이승철")
                .joinYear(19)
                .role(RoleType.ROLE_USER)
                .build();
        memberRepository.save(member);

        // memberMajor
        MemberMajor memberMajor = MemberMajor.builder()
                .majorType(MajorType.주전공)
                .department(department)
                .member(member)
                .build();
        memberMajorRepository.save(memberMajor);

        // course
        List<Course> courses = getCourseInitData();
        courseRepository.saveAll(courses);

        // take
        List<Take> takes = getTakeInitData(courses, member);
        takeRepository.saveAll(takes);
    }

    private List<Take> getTakeInitData(List<Course> courses, Member member) {
        List<Take> takes = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            if (i % 2 == 0) {
                continue;
            }
            Take take = Take.builder()
                    .takenYear("2023")
                    .semester("1")
                    .category(Category.전공필수)
                    .member(member)
                    .course(courses.get(i))
                    .build();
            takes.add(take);
        }
        return takes;
    }

    private List<Course> getCourseInitData() {
        return Arrays.asList(
                Course.builder().code("GEB1112").name("크로스오버 1 : 인간의 탐색").credit(2).build(),
                Course.builder().code("GEB1114").name("크로스오버 3 : 사회의 탐색").credit(2).build(),
                Course.builder().code("GEB1115").name("프로네시스 세미나 Ⅰ : 가치형성과 진로탐색").credit(1).build(),
                Course.builder().code("GEB1124").name("이공계열 글쓰기와 토론").credit(3).build(),
                Course.builder().code("GEB1131").name("생활한문").credit(1).build(),
                Course.builder().code("GEB1107").name("의사소통 영어").credit(3).build(),
                Course.builder().code("GEB1108").name("의사소통 영어: 중급").credit(3).build(),
                Course.builder().code("GEB1109").name("의사소통 영어: 고급").credit(3).build(),
                Course.builder().code("GEB1201").name("실용영어 L/S").credit(3).build(),
                Course.builder().code("GEB1202").name("실용영어 R/W").credit(3).build(),
                Course.builder().code("GEB1203").name("고급대학영어").credit(3).build(),
                Course.builder().code("ACE1204").name("생명과학").credit(4).build(),
                Course.builder().code("ACE1312").name("이산수학").credit(3).build(),
                Course.builder().code("ACE2101").name("공업수학 1").credit(3).build(),
                Course.builder().code("ACE2104").name("통계학").credit(3).build(),
                Course.builder().code("ACE2106").name("정수론입문").credit(3).build(),
                Course.builder().code("MTH1001").name("일반수학 1").credit(3).build(),
                Course.builder().code("MTH1002").name("일반수학 2").credit(3).build(),
                Course.builder().code("PHY1001").name("물리학 1").credit(3).build(),
                Course.builder().code("PHY1002").name("물리학 2").credit(3).build(),
                Course.builder().code("PHY1003").name("물리학실험 1").credit(1).build(),
                Course.builder().code("PHY1004").name("물리학실험 2").credit(1).build(),
                Course.builder().code("CSE1101").name("객체지향프로그래밍 1").credit(3).build(),
                Course.builder().code("CSE1102").name("컴퓨터공학입문 및 실습").credit(3).build(),
                Course.builder().code("CSE1103").name("객체지향프로그래밍 2").credit(3).build(),
                Course.builder().code("CSE2101").name("논리회로").credit(3).build(),
                Course.builder().code("CSE2112").name("자료구조").credit(4).build(),
                Course.builder().code("CSE4205").name("컴퓨터공학 종합설계").credit(3).build(),
                Course.builder().code("ICE4029").name("모바일응용소프트웨어설계").credit(4).build(),
                Course.builder().code("IEN3204").name("정보검색론").credit(3).build(),
                Course.builder().code("CSE1105").name("창의적컴퓨터공학설계").credit(3).build(),
                Course.builder().code("CSE2103").name("어셈블리어").credit(3).build(),
                Course.builder().code("CSE2104").name("인터넷 프로그래밍").credit(3).build(),
                Course.builder().code("CSE2105").name("컴퓨터기반선형대수").credit(3).build(),
                Course.builder().code("CSE2107").name("자바기반응용프로그래밍").credit(3).build(),
                Course.builder().code("CSE3101").name("수치프로그래밍").credit(3).build(),
                Course.builder().code("CSE3201").name("시스템 프로그래밍").credit(3).build(),
                Course.builder().code("CSE3203").name("컴퓨터구조론").credit(3).build(),
                Course.builder().code("CSE3206").name("오퍼레이팅시스템").credit(3).build(),
                Course.builder().code("CSE3305").name("시스템분석").credit(3).build(),
                Course.builder().code("CSE3306").name("문제해결기법").credit(3).build(),
                Course.builder().code("CSE4201").name("소프트웨어공학").credit(3).build(),
                Course.builder().code("CSE4202").name("컴퓨터 네트워크").credit(3).build(),
                Course.builder().code("CSE4204").name("알고리즘").credit(3).build(),
                Course.builder().code("CSE4308").name("컴퓨터보안").credit(3).build(),
                Course.builder().code("CSE3102").name("컴퓨터응용확률").credit(3).build(),
                Course.builder().code("CSE3207").name("데이터베이스").credit(3).build(),
                Course.builder().code("CSE4302").name("인공지능").credit(3).build(),
                Course.builder().code("CSE4305").name("생물의료정보학개론").credit(3).build(),
                Course.builder().code("CSE4311").name("데이터베이스 응용").credit(3).build(),
                Course.builder().code("CSE3202").name("프로그래밍언어론").credit(3).build(),
                Course.builder().code("CSE3205").name("오토마타 및 지능 컴퓨팅").credit(3).build(),
                Course.builder().code("CSE3302").name("마이크로프로세서응용").credit(3).build(),
                Course.builder().code("CSE3303").name("유닉스 프로그래밍").credit(3).build(),
                Course.builder().code("CSE3304").name("임베디드소프트웨어").credit(3).build(),
                Course.builder().code("CSE3307").name("무선통신 및 네트워킹").credit(3).build(),
                Course.builder().code("CSE4312").name("컴파일러").credit(3).build(),
                Course.builder().code("CSE3204").name("컴퓨터그래픽스").credit(3).build(),
                Course.builder().code("CSE4301").name("전자상거래").credit(3).build(),
                Course.builder().code("CSE4303").name("게임프로그래밍").credit(3).build(),
                Course.builder().code("CSE4304").name("영상처리 및 이해").credit(3).build(),
                Course.builder().code("CSE4307").name("멀티미디어 컴퓨팅").credit(3).build(),
                Course.builder().code("CSE3308").name("시스템분석").credit(3).build(),
                Course.builder().code("CSE3309").name("문제해결기법").credit(4).build(),
                Course.builder().code("CSE4314").name("데이터마이닝").credit(3).build(),
                Course.builder().code("CSE3209").name("시스템 프로그래밍").credit(3).build(),
                Course.builder().code("CSE3208").name("오토마타 및 지능 컴퓨팅").credit(3).build(),
                Course.builder().code("GEB1116").name("프로네시스 세미나").credit(2).build(),
                Course.builder().code("GEB1126").name("문제해결을 위한 글쓰기").credit(3).build(),
                Course.builder().code("GEB1143").name("미래사회와 소프트웨어-IT계열").credit(3).build(),
                Course.builder().code("CSE1112").name("컴퓨터공학입문").credit(2).build(),
                Course.builder().code("CSE2113").name("오픈소스SW개론").credit(3).build(),
                Course.builder().code("CSE3212").name("컴퓨터 네트워크").credit(3).build(),
                Course.builder().code("CSE3313").name("리눅스 프로그래밍").credit(3).build(),
                Course.builder().code("CSE3315").name("바이오빅데이터").credit(3).build(),
                Course.builder().code("CSE4315").name("기계학습").credit(3).build(),
                Course.builder().code("CSE4313").name("컴퓨터보안").credit(3).build(),
                Course.builder().code("GEB1151").name("커리어 디자인 2").credit(1).build(),
                Course.builder().code("CSE1312").name("이산구조").credit(3).build(),
                Course.builder().code("CSE3210").name("오픈소스응용프로그래밍").credit(3).build(),
                Course.builder().code("CSE3211").name("클라우드 컴퓨팅").credit(3).build(),
                Course.builder().code("CSE3104").name("컴퓨터응용확률").credit(3).build(),
                Course.builder().code("CSE4232").name("프로그래밍언어 이론").credit(3).build(),
                Course.builder().code("CSE4566").name("바이오빅데이터").credit(3).build()
        );
    }

}