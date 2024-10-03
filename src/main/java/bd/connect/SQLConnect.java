package bd.connect;

import bd.settings.ReadSettings;

import java.sql.*;
import java.util.Map;

public class SQLConnect implements IConnectDataBase {

    private final ReadSettings readSettings = new ReadSettings();
    private static Connection connection;
    private static Statement stmt;

    public void open() {
        Map<String, String> settings = readSettings.getSettings();

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(
                        String.format("%s/%s", settings.get("url"), settings.get("name")),
                        settings.get("username"),
                        settings.get("password"));
            }

            if (stmt == null) {
                stmt = connection.createStatement();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при подключении к базе данных.", e);
        }
    }

    public void close() {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void execute(String sqlRequest) {
        this.open();
        try {
            stmt.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить запрос.", e);
        }
    }

    @Override
    public ResultSet executeQuery(String sqlRequest) {
        this.open();
        try {
            return stmt.executeQuery(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить запрос.", e);
        }
    }

    public int insertInt(String sqlRequest) {
        this.open();
        int id = 0;
        try {
            stmt.execute(sqlRequest, Statement.RETURN_GENERATED_KEYS);
            ResultSet set = stmt.getGeneratedKeys();
            if (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить запрос или получить id.", e);
        }
        return id;
    }
}
