package icurriculum.domain.graduation.dto;

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

    private List<Integer> 핵심교양_미이수영역 = new ArrayList<>();
    private int 핵심교양_이수학점;
    private int 핵심교양_기준학점;


    private int SWAI_이수학점;
    private int SWAI_기준학점;


    private int 창의_이수학점;
    private int 창의_기준학점;


    private int 교양선택_이수학점;
    private int 교양선택_기준학점;

    private List<String> reasons = new ArrayList<>();

    public void 창의_영역수정(int 이수학점, int 기준학점) {
        this.창의_기준학점 = 이수학점;
        this.창의_이수학점 = 기준학점;
    }

    public void SWAI_영역수정(int 이수학점, int 기준학점) {
        this.SWAI_이수학점 = 이수학점;
        this.SWAI_기준학점 = 기준학점;
    }

    public void 핵심교양_영역수정(int 이수학점, int 기준학점) {
        this.핵심교양_이수학점 = 이수학점;
        this.핵심교양_기준학점 = 기준학점;
    }

    public void addReason(String reason) {
        reasons.add(reason);
    }

}
