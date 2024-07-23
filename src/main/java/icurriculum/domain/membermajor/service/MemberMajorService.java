package icurriculum.domain.membermajor.service;

import icurriculum.domain.member.Member;
import icurriculum.domain.membermajor.MemberMajor;
import icurriculum.domain.membermajor.repository.MemberMajorRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberMajorService {

    private final MemberMajorRepository repository;

    public List<MemberMajor> getMemberMajors(Member Member) {
        return repository.findByMember(Member);
    }
}
