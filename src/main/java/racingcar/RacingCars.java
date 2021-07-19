package racingcar;

import java.util.ArrayList;
import java.util.stream.Collectors;

class RacingCars {
    private static final String MOVEMENT_SITUATION = "-";

    private ArrayList<Car> cars;

    private RacingCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public static RacingCars from(ArrayList<Car> cars) {
        return new RacingCars(cars);
    }

    public int size() {
        return this.cars.size();
    }

    public void goForward(final int index, final int randomNumber) {
        if(isNumberMoreThanFour(randomNumber)) {
            this.cars.set(index, this.cars.get(index).changePosition(MOVEMENT_SITUATION));
        }
    }

    public String getNameByIndex(final int index){
        return this.cars.get(index).getName();
    }

    public String getPositonByIndex(final int index){
        return this.cars.get(index).getPosition();
    }

    private boolean isNumberMoreThanFour(final int number) {
        return number >= 4;
    }

    public String getWinner() {
        int max = cars.stream()
                      .max(Car::compareTo)
                      .get().getPositionLength();

        return cars.stream()
                   .filter(car -> car.getPositionLength() == max)
                   .map(car -> car.getName())
                   .collect(Collectors.joining(", "));
    }
}
