package icurriculum.domain.take.service;

import icurriculum.DataInitializer;
import icurriculum.domain.member.Member;
import icurriculum.domain.member.repository.MemberRepository;
import icurriculum.domain.take.Category;
import icurriculum.domain.take.Take;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static icurriculum.domain.take.Category.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class TakeServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TakeService takeService;
    @Autowired
    DataInitializer dataInitializer;

    @Test
    @DisplayName("영역 별 수강 내역 조회 서비스")
    public void getTakeMapForCategory() throws Exception {
        // given
        Member testMember = memberRepository.findById(dataInitializer.getTestMemberId()).get();
        List<Take> actualTakes = dataInitializer.getTakesOnlyData(testMember);

        // when
        Map<Category, List<Take>> expectedTakes = takeService.getTakeMapForCategory(testMember);

        // then
        checkValidSizeForCategory(actualTakes, expectedTakes, 전공필수);
        checkValidSizeForCategory(actualTakes, expectedTakes, 전공선택);
        checkValidSizeForCategory(actualTakes, expectedTakes, 교양필수);
        checkValidSizeForCategory(actualTakes, expectedTakes, 교양선택);
        checkValidSizeForCategory(actualTakes, expectedTakes, SW_AI);
        checkValidSizeForCategory(actualTakes, expectedTakes, 창의);
        checkValidSizeForCategory(actualTakes, expectedTakes, 핵심교양1);
        checkValidSizeForCategory(actualTakes, expectedTakes, 핵심교양2);
        checkValidSizeForCategory(actualTakes, expectedTakes, 핵심교양3);
        checkValidSizeForCategory(actualTakes, expectedTakes, 핵심교양4);
        checkValidSizeForCategory(actualTakes, expectedTakes, 핵심교양5);
        checkValidSizeForCategory(actualTakes, expectedTakes, 핵심교양6);
    }

    void checkValidSizeForCategory(List<Take> actualTakes, Map<Category, List<Take>> expected, Category category) {
        int actualSize = actualTakes.stream()
                .filter(t -> t.getCategory() == category)
                .toList()
                .size();

        List<Take> expectedTakes = expected.get(category);
        if (expectedTakes == null) {
            assertThat(actualSize).isEqualTo(0);
            return;
        }

        assertThat(actualSize).isEqualTo(expectedTakes.size());
    }


}