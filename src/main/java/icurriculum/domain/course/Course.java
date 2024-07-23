package icurriculum.domain.course;

import jakarta.persistence.Column;
public class Course {

    private Long id;

    private String code;

    @Column(name = "course_name")
    private String name;

    private Integer credit;
}
