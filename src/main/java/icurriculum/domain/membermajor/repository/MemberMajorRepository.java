package icurriculum.domain.membermajor.repository;

import icurriculum.domain.member.Member;
import icurriculum.domain.membermajor.MemberMajor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMajorRepository extends JpaRepository<MemberMajor, Long> {
    List<MemberMajor> findByMember(Member Member);
}
