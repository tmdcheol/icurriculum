package icurriculum.domain.course;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "course_id")
    private Long id;

    private String code;

    @Column(name = "course_name")
    private String name;

    private Integer credit;

    @Builder
    public Course(String code, String name, Integer credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
    }
}
