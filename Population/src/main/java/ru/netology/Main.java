package ru.netology;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        // Генерация коллекции из 10 миллионов человек
        Collection<Person> persons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(random.nextInt(names.size())),
                    families.get(random.nextInt(families.size())),
                    random.nextInt(100),
                    Sex.values()[random.nextInt(Sex.values().length)],
                    Education.values()[random.nextInt(Education.values().length)]
            ));
        }

        // 1. Найти количество несовершеннолетних (люди младше 18 лет)
        long underageCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + underageCount);

        // 2. Получить список фамилий призывников (мужчины от 18 до 27 лет)
        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Количество призывников: " + conscripts.size());
        System.out.println("Пример первых 10 фамилий призывников: " +
                conscripts.stream().limit(10).collect(Collectors.toList()));

        // 3. Получить отсортированный по фамилии список потенциально работоспособных людей
        // с высшим образованием (от 18 до 60 лет для женщин и до 65 лет для мужчин)
        List<Person> workablePeople = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> {
                    if (person.getSex() == Sex.WOMAN) {
                        return person.getAge() >= 18 && person.getAge() <= 60;
                    } else {
                        return person.getAge() >= 18 && person.getAge() <= 65;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Количество работоспособных людей с высшим образованием: " + workablePeople.size());
        System.out.println("Пример первых 10 человек:");
        workablePeople.stream()
                .limit(10)
                .forEach(person -> System.out.println("  " + person.getFamily() + " " + person.getName() +
                        ", возраст: " + person.getAge() + ", пол: " + person.getSex()));
    }
}