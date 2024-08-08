package icurriculum.domain.curriculum.util;

import icurriculum.domain.curriculum.CurriculumDecider;
import icurriculum.domain.membermajor.MemberMajor;

public class MajorToDeciderConverter {
    public static CurriculumDecider of(MemberMajor memberMajor) {
        return new CurriculumDecider(
                memberMajor.getMajorType(),
                memberMajor.getDepartment(),
                memberMajor.getMember().getJoinYear()
        );
    }
}
