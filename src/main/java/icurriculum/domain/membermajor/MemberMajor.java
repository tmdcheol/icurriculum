package icurriculum.domain.membermajor;

import icurriculum.domain.department.Department;
import icurriculum.domain.member.Member;
import lombok.Getter;

import static icurriculum.domain.membermajor.MajorType.*;

/**
 * 회원 전공 상태
 */
@Getter
public class MemberMajor {

    private Long id;

    private MajorType majorType;

    private Department department;

    private Member member;

    public boolean is주전공() {
        return majorType == 주전공;
    }

}
