package racing;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.range;

class RacingGame {
    private static final String END_MESSAGE_FORMAT = "실행 결과";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String BLANK = "";

    private final ForwardConditionStrategy strategy;
    private final Racers racers;

    private int stageCount;

    private RacingGame(ForwardConditionStrategy strategy, int stageCount, int carCount) {
        this.strategy = strategy;
        this.stageCount = stageCount;
        this.racers = Racers.from(range(0, carCount).mapToObj(i -> BLANK).collect(toCollection(ArrayList::new)));
    }

    public static RacingGame of(ForwardConditionStrategy strategy, int[] condition) {
        return new RacingGame(strategy, condition[0], condition[1]);
    }

    private StringBuilder resultDataBuilder;

    public String run() {
        resultDataBuilder = new StringBuilder(END_MESSAGE_FORMAT)
                .append(NEW_LINE);

        while (isStageGreaterThanZero()) {
            turnAround();
            resultDataBuilder.append(NEW_LINE);
        }

        return resultDataBuilder.toString().trim();
    }

    private boolean isStageGreaterThanZero() {
        return 0 < stageCount--;
    }

    private void turnAround() {
        racers.turnAround(strategy, resultDataBuilder);
    }
}
