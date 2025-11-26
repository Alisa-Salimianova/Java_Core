package ru.netology;

import java.util.Objects;

public class Person {
    private final String name;
    private final String family;
    private final int age;
    private final Sex sex;
    private final Education education;

    public Person(String name, String family, int age, Sex sex, Education education) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.sex = sex;
        this.education = education;
    }

    public String getName() { return name; }
    public String getFamily() { return family; }
    public int getAge() { return age; }
    public Sex getSex() { return sex; }
    public Education getEducation() { return education; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(family, person.family) &&
                sex == person.sex &&
                education == person.education;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, family, age, sex, education);
    }

    @Override
    public String toString() {
        return family + " " + name + " (" + age + ", " + sex + ", " + education + ")";
    }
}
