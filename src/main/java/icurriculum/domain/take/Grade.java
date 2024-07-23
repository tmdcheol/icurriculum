package icurriculum.domain.take;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {

    A_PLUS(4.5),
    A(4.0),
    B_PLUS(3.5),
    B(3.0),
    C_PLUS(2.5),
    C(2.0),
    D_PLUS(1.5),
    D(1.0),
    P(0.0);

    private final double value;

}

