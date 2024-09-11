package bd.tables;

import menu.AbsAnimals;

import java.sql.ResultSet;
import java.util.List;

public interface ITables {

    void create(List<String> columns);

    void insert(AbsAnimals animals);

    ResultSet select();

    void update(int id, String name, int age, String color, int weight, String typeAnimal);

    ResultSet where(String type);

    void delete();
}
