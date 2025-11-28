public class Main {
    public static void main(String[] args) {
        System.out.println("Демонстрация работы Randoms:");

        for (int r : new Randoms(90, 100)) {
            System.out.println("Случайное число: " + r);
            if (r == 100) {
                System.out.println("Выпало число 100, давайте на этом закончим");
                break;
            }
        }

        // Дополнительная демонстрация с другим диапазоном
        System.out.println("\nДополнительная демонстрация (1-5):");
        int count = 0;
        for (int r : new Randoms(1, 5)) {
            System.out.println("Случайное число: " + r);
            count++;
            if (count == 10) {
                System.out.println("Сгенерировано 10 чисел, завершаем");
                break;
            }
        }
    }
}