package racing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racing.strategy.ForwardConditionStrategy;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

public class RacersTest {
    private final ForwardConditionStrategy stay = () -> true;
    private final ForwardConditionStrategy move = () -> false;

    private Racers racers;

    @BeforeEach
    void setUp() {
        racers = Racers.from((List<? extends Racer>) range(0, 3)
                .mapToObj(it -> Car.of("pobi", ""))
                .collect(toCollection(ArrayList::new)));
    }

    @Test
    @DisplayName("정적_팩토리가_인스턴스를_생성하는지_확인한다")
    void from() throws Exception {
        assertThat(racers).isInstanceOf(Racers.class);
    }

    @Test
    @DisplayName("조회_테스트")
    void get() throws Exception {
        assertThat(racers.get(0).position()).isEqualTo("");
    }

    @Test
    @DisplayName("전진_테스트_조건은_참")
    void goForward() throws Exception {
        racers.turnAround(move, new StringBuilder());
        assertThat(racers.get(0).position()).isEqualTo("-");
    }

    @Test
    @DisplayName("전진_테스트_조건은_거짓")
    void goForwardNot() throws Exception {
        racers.turnAround(stay, new StringBuilder());
        assertThat(racers.get(0).position()).isEqualTo("");
    }

    @Test
    @DisplayName("사이즈_조회_테스트")
    void size() throws Exception {
        assertThat(racers.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("우승자를_반환한다")
    void winner() throws Exception {
        assertThat(racers.winner()).isEqualTo("pobi, pobi, pobi");
    }
}
