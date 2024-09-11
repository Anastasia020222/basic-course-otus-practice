package menu.animals;

import menu.AbsAnimals;
import menu.IFlying;

public class Duck extends AbsAnimals implements IFlying {

    public Duck(String type, String name, int age, String color, int weight) {
        super(type, name, age, color, weight);
    }

    @Override
    public void say() {
        System.out.println("Кря");
    }

    @Override
    public void fly() {
        System.out.println("Я лечу");
    }
}
