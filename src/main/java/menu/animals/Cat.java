package menu.animals;

import menu.AbsAnimals;

public class Cat extends AbsAnimals {

    public Cat(String name, int age, String color, int weight, String type) {
        super(name, age, color, weight, type);
    }

    @Override
    public void say() {
        System.out.println("Мяу");
    }
}
