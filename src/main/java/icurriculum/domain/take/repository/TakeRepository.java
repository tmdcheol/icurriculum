package icurriculum.domain.take.repository;


import icurriculum.domain.member.Member;
import icurriculum.domain.take.Take;

import java.util.List;

public interface TakeRepository {
    List<Take> findByMember(Member member);
}
