package menu.animals;

import menu.AbsAnimals;

public class Dog extends AbsAnimals {

    public Dog(String name, int age, String color, int weight, String type) {
        super(name, age, color, weight, type);
    }

    @Override
    public void say() {
        System.out.println("Гав");
    }
}
