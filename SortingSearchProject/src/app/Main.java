package app;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] numbers = new int[10];
        Random random = new Random();


        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100); // числа від 0 до 99
        }

        System.out.println("Початковий масив:");
        System.out.println(Arrays.toString(numbers));


        ArrayUtils.mergeSort(numbers);
        System.out.println("Відсортований масив:");
        System.out.println(Arrays.toString(numbers));


        int target = numbers[random.nextInt(numbers.length)]; // візьмемо випадкове значення з масиву
        int index = ArrayUtils.binarySearch(numbers, target);

        System.out.printf("Шукаємо елемент %d: %s%n", target,
                (index >= 0 ? "знайдено на позиції " + index : "не знайдено"));
    }
}
