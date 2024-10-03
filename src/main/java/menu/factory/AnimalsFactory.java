package menu.factory;

import menu.AbsAnimals;
import menu.animals.Cat;
import menu.animals.Dog;
import menu.animals.Duck;

public class AnimalsFactory {

    public AbsAnimals createAnimal(AnimalsType animalsType, String name, int age, String color, int weight) {
        switch (animalsType) {
            case CAT:
                return new Cat(animalsType.name().toLowerCase(), name, age, color, weight);
            case DOG:
                return new Dog(animalsType.name().toLowerCase(), name, age, color, weight);
            case DUCK:
                return new Duck(animalsType.name().toLowerCase(), name, age, color, weight);
            default:
                System.out.println("Нет такого типа животного.");
                return null;
        }
    }
}
