package icurriculum.domain.take.repository;

import icurriculum.DataInitializer;
import icurriculum.domain.member.Member;
import icurriculum.domain.member.repository.MemberRepository;
import icurriculum.domain.take.Take;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TakeRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TakeRepository takeRepository;
    @Autowired
    DataInitializer dataInitializer;


    @Test
    @DisplayName("수강목록 조회 확인")
    public void getTakeMapForCategory() throws Exception {
        // given
        Long testMemberId = dataInitializer.getTestMemberId();
        Member testMember = memberRepository.findById(testMemberId).get();
        List<Take> testTakes = takeRepository.findByMember(testMember);

        // when
        List<Take> findTakes = takeRepository.findByMember(testMember);

        // then
        assertThat(testTakes.size()).isEqualTo(findTakes.size());
    }

}