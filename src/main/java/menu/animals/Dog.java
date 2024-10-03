package menu.animals;

import menu.AbsAnimals;

public class Dog extends AbsAnimals {

    public Dog(String type, String name, int age, String color, int weight) {
        super(type, name, age, color, weight);
    }

    @Override
    public void say() {
        System.out.println("Гав");
    }
}
