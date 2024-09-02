package animals;

public abstract class AbsAnimals {

    private String name;
    private int age;
    private int weight;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void say() {
        System.out.println("Я говорю");
    }

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    private String selectAge(int age) {
        int remainder10 = age % 10;

        if (age >= 11 && age <= 14) {
            return "лет";
        } else if (remainder10 == 1) {
            return "год";
        } else if (remainder10 > 1 && remainder10 <= 4) {
            return "года";
        } else {
            return "лет";
        }
    }

    @Override
    public String toString() {
        return "Привет! Меня зовут " + name
                + ", мне " + age + " " + selectAge(age) + ", "
                + "я вешу " + weight
                + "кг, мой цвет: " + color + ".";
    }
}
