package utils;

public class QuerySQL {

    public static final String GET_TABLE = "SELECT * FROM %s";
    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS %s";
    public static final String CREATE_TABLE = "CREATE TABLE %s (%s)";
    public static final String UPDATE_TABLE = "UPDATE %s SET name='%s', age='%s', color='%s', weight='%s', type='%s' WHERE id = %d";
    public static final String INSERT_TABLE = "INSERT INTO %s (name, age, color, weight, type) " +
            "VALUES ('%s', '%d', '%s', '%d', '%s')";
    public static final String WHERE_TABLE = "SELECT * FROM %s WHERE type = '%s'";
    public static final String GET_DATA_ID = "SELECT * FROM animals WHERE id = %d";
}
