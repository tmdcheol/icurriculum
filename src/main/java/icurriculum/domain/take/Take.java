package icurriculum.domain.take;

import icurriculum.domain.member.Member;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class Take {

    /**
     * id
     * 영역
     * 성적
     * 이수년도
     * 학수번호
     * 과목명
     * 학점
     * 회원
     */

    private Long id;

    private Category category;

    private Grade Grade;

    private String year;

    private String semester;

    private String code;

    @Column(name = "course_name")
    private String name;

    private Integer credit;

    private Member member;

}
