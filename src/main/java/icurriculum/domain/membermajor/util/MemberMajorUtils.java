package icurriculum.domain.membermajor.util;

import icurriculum.domain.membermajor.MemberMajor;

import java.util.List;

public class MemberMajorUtils {

    /**
     * 주전공 MemberMajor return
     */
    public static MemberMajor getMainMemberMajor(List<MemberMajor> memberMajors) {
        return memberMajors.stream()
                .filter(MemberMajor::isMain)
                .findAny()
                .orElseThrow(RuntimeException::new); // 예외 추후 정의
    }

}
