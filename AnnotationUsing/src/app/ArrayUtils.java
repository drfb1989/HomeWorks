package app;


public class ArrayUtils {

    @MethodInfo(
            name = "printArray",
            returnType = "void",
            description = "Виводить елементи масиву на екран"
    )
    @Author(firstName = "Артем", lastName = "Іваненко")
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    @MethodInfo(
            name = "sumArray",
            returnType = "int",
            description = "Обчислює суму всіх елементів масиву"
    )
    @Author(firstName = "Артем", lastName = "Іваненко")
    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    @MethodInfo(
            name = "findMin",
            returnType = "int",
            description = "Знаходить мінімальне значення в масиві"
    )
    @Author(firstName = "Артем", lastName = "Іваненко")
    public static int findMin(int[] arr) {
        int min = arr[0];
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
}
