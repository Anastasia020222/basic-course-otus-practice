package menu.animals;

import menu.AbsAnimals;
import menu.IFlying;

public class Duck extends AbsAnimals implements IFlying {

    public Duck(String name, int age, String color, int weight, String type) {
        super(name, age, color, weight, type);
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
