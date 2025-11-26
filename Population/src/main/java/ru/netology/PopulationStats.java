package ru.netology;

import java.util.*;
import java.util.stream.Collectors;

public class PopulationStats {

    // 1) количество несовершеннолетних
    public static long countUnderage(Collection<Person> persons) {
        return persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
    }

    // 2) список фамилий призывников (мужчины 18..27 включительно)
    public static List<String> getConscriptsFamilies(Collection<Person> persons) {
        return persons.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
    }

    // 3) отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
    // женщины 18..60 включительно, мужчины 18..65 включительно
    public static List<Person> getWorkablePeopleWithHigherEducation(Collection<Person> persons) {
        return persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> {
                    if (p.getSex() == Sex.WOMAN) {
                        return p.getAge() >= 18 && p.getAge() <= 60;
                    } else {
                        return p.getAge() >= 18 && p.getAge() <= 65;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }

    public static List<Person> samplePeople() {
        return Arrays.asList(
                new Person("Jack", "Evans", 17, Sex.MAN, Education.SECONDARY),
                new Person("Connor", "Young", 19, Sex.MAN, Education.HIGHER),
                new Person("Harry", "Harris", 26, Sex.MAN, Education.FURTHER),
                new Person("George", "Wilson", 30, Sex.MAN, Education.HIGHER),
                new Person("Samuel", "Davies", 45, Sex.MAN, Education.HIGHER),
                new Person("Anna", "Petrova", 22, Sex.WOMAN, Education.HIGHER),
                new Person("Olga", "Ivanova", 61, Sex.WOMAN, Education.HIGHER),
                new Person("Marina", "Sidorova", 59, Sex.WOMAN, Education.HIGHER),
                new Person("John", "Adamson", 25, Sex.MAN, Education.HIGHER)
        );
    }
}
