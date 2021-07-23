package racing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class RacersTest {
    private final ForwardConditionStrategy mockStrategy = BDDMockito.mock(NumberMoreThanFourConditionStrategy.class);

    private Racers racers;

    @BeforeEach
    void setUp() {
        ArrayList<String> list = new ArrayList<>();
        list.add("-");
        list.add("-");
        list.add("-");
        racers = Racers.from(list);
    }

    @Test
    @DisplayName("정적_팩토리가_인스턴스를_생성하는지_확인한다")
    void from() throws Exception {
        assertThat(racers).isInstanceOf(Racers.class);
    }

    @Test
    @DisplayName("조회_테스트")
    void get() throws Exception {
        assertThat(racers.get(0)).isEqualTo("-");
    }

    @Test
    @DisplayName("전진_테스트_조건은_참")
    void goForward() throws Exception {
        BDDMockito.given(mockStrategy.judgeCondition()).willReturn(false);
        racers.turnAround(mockStrategy, new StringBuilder());
        assertThat(racers.get(0)).isEqualTo("--");
    }

    @Test
    @DisplayName("전진_테스트_조건은_거짓")
    void goForwardNot() throws Exception {
        BDDMockito.when(mockStrategy.judgeCondition()).thenReturn(true);
        racers.turnAround(mockStrategy, new StringBuilder());
        assertThat(racers.get(0)).isEqualTo("-");
    }

    @Test
    @DisplayName("사이즈_조회_테스트")
    void size() throws Exception {
        assertThat(racers.size()).isEqualTo(3);
    }
}
