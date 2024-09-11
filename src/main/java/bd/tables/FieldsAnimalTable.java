package bd.tables;

public enum FieldsAnimalTable {

    ID,
    NAME,
    AGE,
    COLOR,
    WEIGHT,
    TYPE;

    public static FieldsAnimalTable getField(String animalsType) {
        return FieldsAnimalTable.valueOf(animalsType.trim().toUpperCase());
    }
}
