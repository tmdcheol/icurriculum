package icurriculum.domain.take.repository;


import icurriculum.domain.member.Member;
import icurriculum.domain.take.Take;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakeRepository extends JpaRepository<Take, Long> {
    List<Take> findByMember(Member member);
}
