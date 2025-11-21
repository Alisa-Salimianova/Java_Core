import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
//ИСПОЛЬЗУЕМ КОЛЛЕКЦИИ, А НЕ STREAM API

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        List<Integer> positiveNumbers = new ArrayList<>();

        for (Integer num : intList) {
            if (num > 0) {
                positiveNumbers.add(num);
            }
        }

        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer num : positiveNumbers) {
            if (num % 2 == 0) {
                evenNumbers.add(num);
            }
        }

        Collections.sort(evenNumbers);

        System.out.println("Результат (без Stream API):");
        for (Integer num : evenNumbers) {
            System.out.print(num + " ");
        }
    }
}
 */

// ИСПОЛЬЗУЕМ STREAM API
public class StreamMain {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        List<Integer> result = intList.stream()
                .filter(x -> x > 0)
                .filter(x -> x % 2 == 0)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Обработанный список чисел:");
        result.forEach(x -> System.out.print(x + " "));
    }
}