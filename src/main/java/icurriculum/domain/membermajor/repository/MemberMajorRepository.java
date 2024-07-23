package icurriculum.domain.membermajor.repository;

import icurriculum.domain.member.Member;
import icurriculum.domain.membermajor.MemberMajor;

import java.util.List;

public interface MemberMajorRepository {
    List<MemberMajor> findByMember(Member Member);
}
