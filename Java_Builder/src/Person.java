import java.util.OptionalInt;

public class Person {

    protected final String name;
    protected final String surname;
    protected int age = -1;
    protected String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public boolean hasAge() {
        return age >= 0;
    }

    public boolean hasAddress() {
        return address != null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return hasAge() ? OptionalInt.of(age) : OptionalInt.empty();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        if (!hasAge()) {
            throw new IllegalStateException("Возраст неизвестен — нельзя сделать happyBirthday()");
        }
        age++;
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder pb = new PersonBuilder();
        pb.setSurname(this.surname);
        if (this.hasAge()) {
            pb.setAge(0); // ребёнок → начальный возраст 0
        }
        if (this.hasAddress()) {
            pb.setAddress(this.address);
        }
        return pb;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', surname='" + surname + "'" +
                (hasAge() ? ", age=" + age : "") +
                (hasAddress() ? ", address='" + address + "'" : "") +
                "}";
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
