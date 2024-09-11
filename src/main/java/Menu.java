import bd.tables.TablesAnimals;
import menu.AbsAnimals;
import menu.factory.AnimalsFactory;
import menu.factory.AnimalsType;
import utils.CommandsMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static menu.factory.AnimalsType.getAnimalsType;


public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AnimalsFactory animalsFactory = new AnimalsFactory();
    private static final ArrayList<AbsAnimals> listAnimals = new ArrayList<>();
    private static final TablesAnimals tablesAnimals = new TablesAnimals();

    public static void main(String[] args) {
        tablesAnimals.createAnimalTable();

        CommandsMenu com;
        boolean exit = false;
        AbsAnimals animal;

        System.out.println("Привет!");

        while (!exit) {

            System.out.println("Введи команду: add/list/edit/filter/exit");
            String input = scanner.nextLine();

            try {
                com = CommandsMenu.getCommandMenu(input);
            } catch (IllegalArgumentException ex) {
                System.out.println("Вы ввели неверную команду.");
                continue;
            }

            switch (com) {
                case ADD:
                    animal = stepsAddAnimal();
                    tablesAnimals.insert(animal);
                    listAnimals.add(animal);
                    animal.say();
                    break;
                case LIST:
                    printAnimal(listAnimals);
                    break;
                case EDIT:
                    tablesAnimals.selectAnimalTable();
                    if (listAnimals.isEmpty()) {
                        System.out.println("Список пуст. Сначала добавьте питомца.");
                        continue;
                    }
                    stepEditAnimalTable();
                    break;
                case FILTER:
                    if (listAnimals.isEmpty()) {
                        System.out.println("Список пуст. Сначала добавьте питомца.");
                        continue;
                    }
                    stepFilterAnimalTable();
                    break;
                case EXIT:
                    tablesAnimals.exitConnect();
                    exit = true;
                    break;
            }
        }
        scanner.close();
    }

    public static AbsAnimals stepsAddAnimal() {
        AnimalsType typeAnimal = typeAnimal("Введите животное: cat/dog/duck");
        String name = getString("Дайте имя своему питомцу:");
        String color = getString("Какой цвет у твоего питомца?");
        int age = numberData("Сколько лет твоему питомцу?");
        int weight = numberData("Какой вес у твоего питомца?");
        return animalsFactory.createAnimal(name, age, color, weight, typeAnimal);
    }

    public static void stepEditAnimalTable() {
        int id = getId("Введите id животного из таблицы, которого будем редактировать:");
        String field = getString("Введите поле, которое меняем: name, age, weight, color, type (несколько полей введите через запятую)");
        String newValue = getString("Введите новое значение для поля:");
        try {
            tablesAnimals.updateAnimalTable(id, field, newValue);
            System.out.println("Успех!");
            tablesAnimals.updateAnimalById(id, listAnimals);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            stepEditAnimalTable();
        }
    }

    public static void stepFilterAnimalTable() {
        String type = typeAnimal("Введите тип животного: cat/dog/duck").name();
        tablesAnimals.whereAnimalTable(type);
    }

    public static int getId(String ms) {
        int id;
        boolean idExit = false;
        while (true) {
            id = numberData(ms);
            for (AbsAnimals s : listAnimals) {
                if (s.getId() == id) {
                    idExit = true;
                    break;
                }
            }
            if (idExit) {
                return id;
            } else {
                System.out.println("Вы ввели несуществующий id. Попробуйсте еще раз.");
            }
        }
    }

    public static AnimalsType typeAnimal(String msg) {
        while (true) {
            System.out.println(msg);
            String typeAnimal = scanner.nextLine();
            try {
                return getAnimalsType(typeAnimal);
            } catch (IllegalArgumentException ex) {
                System.out.println("Вы ввели неверный тип животного.");
            }
        }
    }

    public static int numberData(String msg) {
        int number;
        while (true) {
            System.out.println(msg);
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number >= 0) {
                    return number;
                } else {
                    System.out.println("Вы ввели отрицательное число.");
                }
            } catch (Exception ex) {
                System.out.println("Вы ввесли неверный формат данных. Введите число.");
            }
        }
    }

    public static String getString(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }

    public static void printAnimal(List<AbsAnimals> list) {
        System.out.println("Лист с животными: ");
        for (AbsAnimals animal : list) {
            System.out.println(animal);
        }
    }
}