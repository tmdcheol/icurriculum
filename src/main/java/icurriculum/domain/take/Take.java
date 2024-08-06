package icurriculum.domain.take;

import icurriculum.domain.course.Course;
import icurriculum.domain.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Take {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "take_id")
    private Long id;

    @Enumerated(STRING)
    private Category category;

    private String takenYear;

    private String semester;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Builder
    public Take(Category category, String takenYear, String semester, Member member, Course course) {
        this.category = category;
        this.takenYear = takenYear;
        this.semester = semester;
        this.member = member;
        this.course = course;
    }
}
