package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Person;
import ru.netology.PopulationStats;

import java.util.List;

class PopulationStatsTest {

    private List<Person> people;

    @BeforeEach
    void setUp() {
        people = PopulationStats.samplePeople();
    }

    @Test
    void testCountUnderage() {
        long underage = PopulationStats.countUnderage(people);
        Assertions.assertEquals(1, underage, "Должен быть один несовершеннолетний");
    }

    @Test
    void testGetConscriptsFamilies() {
        List<String> conscripts = PopulationStats.getConscriptsFamilies(people);
        Assertions.assertTrue(conscripts.contains("Young"));
        Assertions.assertTrue(conscripts.contains("Harris"));
        Assertions.assertTrue(conscripts.contains("Adamson"));
        Assertions.assertEquals(3, conscripts.size());
    }

    @Test
    void testGetWorkablePeopleWithHigherEducation_sortedAndFiltered() {
        List<Person> workable = PopulationStats.getWorkablePeopleWithHigherEducation(people);
        Assertions.assertEquals(6, workable.size());
        Assertions.assertEquals("Adamson", workable.get(0).getFamily());
    }
}
