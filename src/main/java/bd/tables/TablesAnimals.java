package bd.tables;

import menu.AbsAnimals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TablesAnimals extends AbsTables {

    private static final String tableName = "animals";

    public TablesAnimals() {
        super(tableName);
    }

    public void createAnimalTable() {
        List<String> columnsAnimalTable = new ArrayList<>();
        columnsAnimalTable.add("id INT AUTO_INCREMENT PRIMARY KEY");
        columnsAnimalTable.add("name VARCHAR(20)");
        columnsAnimalTable.add("age INT");
        columnsAnimalTable.add("color VARCHAR(20)");
        columnsAnimalTable.add("weight INT");
        columnsAnimalTable.add("type VARCHAR(20)");
        create(columnsAnimalTable);
    }

    public void selectAnimalTable() {
        ResultSet rs = select();
        printTable(rs);
    }

    public void whereAnimalTable(String type) {
        ResultSet rs = where(type);
        printTable(rs);
    }

    public void updateAnimalTable(int id, List<AbsAnimals> listAnimals) {
        for (AbsAnimals an : listAnimals) {
            if (an.getId() == id) {
                update(id, an.getName(), an.getAge(), an.getColor(), an.getWeight(), an.getType());
                break;
            }
        }
    }

    public void printTable(ResultSet rs) {
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s%n", "ID", "Name", "Age", "Color", "Weight", "Type");
        System.out.println("------------------------------------------------------------");

        try {
            while (rs.next()) {
                System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("color"),
                        rs.getFloat("weight"), rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось распечатать список животных.", e);
        }
    }

    public void exitConnect() {
        sqlConnect.close();
    }
}
