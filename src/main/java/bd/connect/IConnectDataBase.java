package bd.connect;

import java.sql.ResultSet;

public interface IConnectDataBase {
    void execute(String sqlRequest);

    ResultSet executeQuery(String sqlRequest);
}
