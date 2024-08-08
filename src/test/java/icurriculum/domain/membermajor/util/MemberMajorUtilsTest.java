package icurriculum.domain.membermajor.util;

import icurriculum.domain.membermajor.MemberMajor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static icurriculum.domain.membermajor.util.MemberMajorUtils.getMainMemberMajor;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MemberMajorUtilsTest {

    @Mock
    MemberMajor mainMajor;
    @Mock
    MemberMajor notMainMajor;

    @Test
    @DisplayName("주전공 포함한 상태일때 정상 리턴")
    public void getMainMemberMajor_주전공포함상태() {
        // given
        when(mainMajor.isMain()).thenReturn(true);
        List<MemberMajor> memberMajors = Arrays.asList(mainMajor, notMainMajor);

        // when
        MemberMajor expected = getMainMemberMajor(memberMajors);

        // then
        Assertions.assertThat(mainMajor).isEqualTo(expected);
    }

    @Test
    @DisplayName("주전공 미포함한 상태일때 에러 발생")
    public void getMainMemberMajor_주전공미포함상태() {
        // given
        when(notMainMajor.isMain()).thenReturn(false);
        List<MemberMajor> memberMajors = Arrays.asList(notMainMajor);

        // when & then
        assertThrows(RuntimeException.class, () -> {
            getMainMemberMajor(memberMajors);
        });
    }

    @Test
    @DisplayName("전공상태 empty 에러 발생")
    public void getMainMemberMajor_전공empty상태() {
        // given
        List<MemberMajor> memberMajors = new ArrayList<>();

        // when & then
        assertThrows(RuntimeException.class, () -> {
            getMainMemberMajor(memberMajors);
        });
    }
}