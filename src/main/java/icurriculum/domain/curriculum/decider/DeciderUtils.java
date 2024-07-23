package icurriculum.domain.curriculum.decider;

import icurriculum.domain.membermajor.MajorType;
import icurriculum.domain.membermajor.MemberMajor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static icurriculum.domain.curriculum.decider.CurriculumDecider.*;


public class DeciderUtils {

    public static CurriculumDecider generateDeciderOnly주전공(List<MemberMajor> memberMajors) {
        Optional<CurriculumDecider> decider = memberMajors.stream()
                .filter(MemberMajor::is주전공)
                .map(m -> createDecider(m.getMajorType(), m.getDepartment(), m.getMember().getJoinYear()))
                .findAny();

        /**
         * 예외 추후 정의
         */
        return decider
                .orElseThrow(RuntimeException::new);
    }

    public static List<CurriculumDecider> generateDecidersAll(List<MemberMajor> memberMajors) {
        return memberMajors.stream()
                .map(m -> createDecider(m.getMajorType(), m.getDepartment(), m.getMember().getJoinYear()))
                .collect(Collectors.toList());
    }

}
