import animals.AbsAnimals;
import animals.Cat;
import animals.Dog;
import animals.Duck;
import menu.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Commands com;
        boolean exit = false;
        ArrayList<AbsAnimals> animals = new ArrayList<>();

        System.out.println("Привет!");

        while (!exit) {

            System.out.println("Введи команду: add/list/exit");
            String input = scanner.nextLine();

            try {
                com = Commands.getCommand(input);
            } catch (IllegalArgumentException ex) {
                System.out.println("Вы ввели не верную команду.");
                continue;
            }

            switch (com) {
                case ADD:
                    AbsAnimals animal = typeAnimal();

                    System.out.println("Дай имя своему питомцу:");
                    String name = scanner.nextLine();

                    System.out.println("Какой цвет у твоего питомца?");
                    String color = scanner.nextLine();

                    int age = numberData("Сколько лет твоему питомцу?");
                    int weight = numberData("Какой вес у твоего питомца?");

                    animal.setName(name);
                    animal.setColor(color);
                    animal.setAge(age);
                    animal.setWeight(weight);

                    animals.add(animal);
                    animal.say();
                    break;
                case LIST:
                    printAnimal(animals);
                    break;
                case EXIT:
                    exit = true;
                    break;
            }
        }
        scanner.close();
    }

    public static AbsAnimals typeAnimal() {
        while (true) {
            System.out.println("Введи животное: cat/dog/duck");
            String typeAnimal = scanner.nextLine();

            switch (typeAnimal.toLowerCase()) {
                case "cat":
                    return new Cat();
                case "dog":
                    return new Dog();
                case "duck":
                    return new Duck();
                default:
                    System.out.println("Нет такого типа животного.");
                    break;
            }
        }
    }

    public static int numberData(String msg) {
        while (true) {
            System.out.println(msg);
            int number = Integer.parseInt(scanner.nextLine());
            try {
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Вы ввели отрицательное число.");
                }
            } catch (Exception ex) {
                System.out.println("Вы ввесли неверный формат данных. Введите число.");
            }
        }
    }

    public static void printAnimal(List<AbsAnimals> list) {
        System.out.println("Лист с животными: ");
        for (AbsAnimals animal : list) {
            System.out.println(animal);
        }
    }
}