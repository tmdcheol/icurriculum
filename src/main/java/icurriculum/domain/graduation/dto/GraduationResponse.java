package icurriculum.domain.graduation.dto;

import icurriculum.domain.take.Category;

import java.util.*;

public class GraduationResponse {
    private int 전체_이수_학점;
    private int 전체_기준_학점;

    private int 주전공필수_이수학점;
    private int 주전공필수_기준학점;

    private int 주전공선택_이수학점;
    private int 주전공선택_기준학점;

    private int 교양필수_이수학점;
    private int 교양필수_기준학점;

    private boolean 핵심교양_조건충족;
    private final List<Category> 핵심교양_미이수영역 = new ArrayList<>();

    private boolean SWAI_조건충족;

    private boolean 창의_조건충족;

    public void 창의_영역_입력(int 이수학점, int 기준학점) {
        add_전체이수학점(이수학점);
        창의_조건충족 = 이수학점 >= 기준학점;
    }

    public void SWAI_영역_입력(int 이수학점, int 기준학점) {
        add_전체이수학점(이수학점);
        SWAI_조건충족 = 이수학점 >= 기준학점;
    }

    public void 핵심교양_영역_입력_영역상관없을때(int 이수학점, int 기준학점) {
        add_전체이수학점(이수학점);
        핵심교양_조건충족 = 이수학점 >= 기준학점;
    }

    public void 핵심교양_영역_입력_영역상관있을때(int 이수학점, List<Category> 미이수영역) {
        if (미이수영역.isEmpty()) {
            핵심교양_조건충족 = true;
            return;
        }
        add_전체이수학점(이수학점);
        핵심교양_조건충족 = false;
        핵심교양_미이수영역.addAll(미이수영역);
    }

    private void add_전체이수학점(int credit){
        전체_이수_학점 += credit;
    }

}
