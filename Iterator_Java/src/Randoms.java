import java.util.Iterator;
import java.util.Random;

public class Randoms implements Iterable<Integer> {
    protected Random random;
    private int min;
    private int max;

    public Randoms(int min, int max) {
        this.random = new Random();
        this.min = min;
        this.max = max;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Integer> {
        @Override
        public boolean hasNext() {
            // Итератор бесконечный, всегда возвращает true
            return true;
        }

        @Override
        public Integer next() {
            // Генерируем случайное число в диапазоне [min, max]
            return random.nextInt(max - min + 1) + min;
        }
    }
}