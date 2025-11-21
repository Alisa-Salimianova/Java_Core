import java.util.function.*;


public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        try {
            int a = calc.plus.apply(1, 2);
            int b = calc.minus.apply(1, 1);
            int c = calc.devide.apply(a, b);
            calc.println.accept(c);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nАльтернативная реализация:");
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);

        if (b != 0) {
            int c = calc.devide.apply(a, b);
            calc.println.accept(c);
        } else {
            System.out.println("Ошибка: деление на ноль невозможно");
        }

        System.out.println("\nДругие операции:");
        calc.println.accept(calc.pow.apply(5)); // 25
        calc.println.accept(calc.abs.apply(-10)); // 10
        System.out.println("Число положительное: " + calc.isPositive.test(5)); // true
    }
}