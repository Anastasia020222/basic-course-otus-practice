package bd.tables;

import bd.connect.SQLConnect;
import menu.AbsAnimals;

import java.sql.ResultSet;
import java.util.List;

import static utils.QuerySQL.*;

public abstract class AbsTables implements ITables {

    protected final SQLConnect sqlConnect;
    private final String tableName;

    AbsTables(String tableName) {
        this.sqlConnect = new SQLConnect();
        this.tableName = tableName;
    }

    @Override
    public void create(List<String> columns) {
        sqlConnect.execute(String.format(CREATE_TABLE, tableName, String.join(",", columns)));
    }

    @Override
    public void insert(AbsAnimals animals) {
        int id = sqlConnect.insertInt(String.format(INSERT_TABLE, tableName, animals.getName(), animals.getAge(),
                animals.getColor(), animals.getWeight(), animals.getType()));
        animals.setId(id);
    }

    @Override
    public ResultSet select() {
        return sqlConnect.executeQuery(String.format(GET_TABLE, tableName));
    }

    @Override
    public void update(int id, String name, int age, String color, int weight, String typeAnimal) {
        sqlConnect.execute(String.format(UPDATE_TABLE, tableName, name, age, color, weight, typeAnimal, id));
    }

    @Override
    public ResultSet where(String type) {
        return sqlConnect.executeQuery(String.format(WHERE_TABLE, tableName, type));
    }

    @Override
    public void delete() {
        sqlConnect.execute(String.format(DELETE_TABLE, tableName));
    }

    public ResultSet getDataById(int id) {
        return sqlConnect.executeQuery(String.format(GET_DATA_ID, id));
    }
}
