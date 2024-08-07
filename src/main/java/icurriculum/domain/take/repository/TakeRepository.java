package icurriculum.domain.take.repository;


import icurriculum.domain.member.Member;
import icurriculum.domain.take.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TakeRepository extends JpaRepository<Take, Long> {

    @Query("select t from Take t join fetch t.member m left join fetch t.course c where t.member = :member")
    List<Take> findByMember(Member member);

}
