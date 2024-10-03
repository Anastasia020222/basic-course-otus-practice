package menu.animals;

import menu.AbsAnimals;

public class Cat extends AbsAnimals {

    public Cat(String type, String name, int age, String color, int weight) {
        super(type, name, age, color, weight);
    }

    @Override
    public void say() {
        System.out.println("Мяу");
    }
}
