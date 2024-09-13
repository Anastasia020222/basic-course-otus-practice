import bd.tables.FieldsAnimalTable;
import bd.tables.TablesAnimals;
import menu.AbsAnimals;
import menu.factory.AnimalsFactory;
import menu.factory.AnimalsType;
import utils.CommandsMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static menu.factory.AnimalsType.getAnimalsType;

public class MenuRun {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AnimalsFactory animalsFactory = new AnimalsFactory();
    private static ArrayList<AbsAnimals> listAnimals = new ArrayList<>();
    private static final TablesAnimals tablesAnimals = new TablesAnimals();

    public static void main(String[] args) {
        CommandsMenu com;
        boolean exit = false;
        AbsAnimals animal;

        try {
            listAnimals = tablesAnimals.getAnimal();
        } catch (RuntimeException ex) {
            tablesAnimals.createAnimalTable();
        }

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
                    animal = stepsADDAnimal();
                    tablesAnimals.insert(animal);
                    listAnimals.add(animal);
                    animal.say();
                    break;
                case LIST:
                    if (listAnimals.isEmpty()) {
                        System.out.println("Список пуст. Сначала добавьте питомца.");
                        continue;
                    }
                    tablesAnimals.selectAnimalTable();
                    break;
                case EDIT:
                    if (listAnimals.isEmpty()) {
                        System.out.println("Список пуст. Сначала добавьте питомца.");
                        continue;
                    }
                    tablesAnimals.selectAnimalTable();
                    int id = getIdAnimal();
                    tablesAnimals.updateAnimalTable(id, stepsUpdateAnimal(id));
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

    public static AbsAnimals stepsADDAnimal() {
        return animalsFactory.createAnimal(getTypeAnimal(), getNameAnimal(), getAgeAnimal(), getColorAnimal(), getWeightAnimal());
    }

    public static void stepFilterAnimalTable() {
        String type = getTypeAnimal().name();
        tablesAnimals.whereAnimalTable(type);
    }

    public static List<AbsAnimals> stepsUpdateAnimal(int id) {
        String[] field = getField();
        FieldsAnimalTable fieldName;

        for (AbsAnimals an : listAnimals) {
            if (an.getId() == id) {
                for (int i = 0; i < field.length; i++) {
                    fieldName = FieldsAnimalTable.getField(field[i]);
                    switch (fieldName) {
                        case NAME:
                            an.setName(getNameAnimal());
                            break;
                        case AGE:
                            an.setAge(getAgeAnimal());
                            break;
                        case COLOR:
                            an.setColor(getColorAnimal());
                            break;
                        case WEIGHT:
                            an.setWeight(getWeightAnimal());
                            break;
                        case TYPE:
                            an.setType(getTypeAnimal().name());
                            break;
                    }
                }
                break;
            }
        }
        return listAnimals;
    }

    public static String[] getField() {
        String[] field;
        while (true) {
            field = getStringData("Введите поле, которое меняем: all или name, age, weight, color, type " +
                    "(одно или несколько через запятую, например: name,type) ").split(",");
            boolean isValid = true;
            for (String tableName : field) {
                if (tableName.equalsIgnoreCase("all")) {
                    field = new String[]{"name", "age", "weight", "color", "type"};
                    break;
                }
                try {
                    FieldsAnimalTable.getField(tableName.trim());
                } catch (IllegalArgumentException ex) {
                    System.out.println("Вы ввели неверное поле: " + tableName + ". Попробуйте еще раз.");
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                return field;
            }
        }
    }

    public static String getNameAnimal() {
        return getStringData("Введите имя питомца:");
    }

    public static int getAgeAnimal() {
        return getNumberData("Введите сколько лет питомцу:");
    }

    public static String getColorAnimal() {
        return getStringData("Укажите цвет питомца:");
    }

    public static int getWeightAnimal() {
        return getNumberData("Задайте вес питомцу:");
    }

    public static AnimalsType getTypeAnimal() {
        while (true) {
            System.out.println(("Введите тип питомца: cat/dog/duck"));
            String typeAnimal = scanner.nextLine();
            try {
                return getAnimalsType(typeAnimal);
            } catch (IllegalArgumentException ex) {
                System.out.println("Вы ввели неверный тип животного. Введите еще раз.");
            }
        }
    }

    public static int getIdAnimal() {
        return getId("Введите id животного из таблицы, которого будем редактировать:");
    }

    public static int getId(String ms) {
        int id;
        boolean idExit = false;
        while (true) {
            id = getNumberData(ms);
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

    private static String getStringData(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static int getNumberData(String msg) {
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
}
