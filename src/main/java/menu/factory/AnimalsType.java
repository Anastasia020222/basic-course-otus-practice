package menu.factory;

public enum AnimalsType {

    CAT,
    DOG,
    DUCK;

    public static AnimalsType getAnimalsType(String animalsType) {
        return AnimalsType.valueOf(animalsType.trim().toUpperCase());
    }
}
