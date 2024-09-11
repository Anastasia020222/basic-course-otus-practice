package menu.factory;

import menu.AbsAnimals;
import menu.animals.Cat;
import menu.animals.Dog;
import menu.animals.Duck;

public class AnimalsFactory {

    public AbsAnimals createAnimal(String name, int age, String color, int weight, AnimalsType animalsType) {
        switch (animalsType) {
            case CAT:
                return new Cat(name, age, color, weight, animalsType.name().toLowerCase());
            case DOG:
                return new Dog(name, age, color, weight, animalsType.name().toLowerCase());
            case DUCK:
                return new Duck(name, age, color, weight, animalsType.name().toLowerCase());
            default:
                System.out.println("Нет такого типа животного.");
                return null;
        }
    }
}
