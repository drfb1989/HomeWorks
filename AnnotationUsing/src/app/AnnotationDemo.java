package app;

import java.lang.reflect.Method;

public class AnnotationDemo {
    public static void main(String[] args) {

        int[] numbers = {5, 2, 9, 1, 7};


        ArrayUtils.printArray(numbers);
        System.out.println("Сума: " + ArrayUtils.sumArray(numbers));
        System.out.println("Мінімум: " + ArrayUtils.findMin(numbers));

        System.out.println("\n=== Інформація про методи (через анотації) ===");


        Method[] methods = ArrayUtils.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodInfo.class) && method.isAnnotationPresent(Author.class)) {
                MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                Author author = method.getAnnotation(Author.class);

                System.out.println("Метод: " + methodInfo.name());
                System.out.println("Тип повернення: " + methodInfo.returnType());
                System.out.println("Опис: " + methodInfo.description());
                System.out.println("Автор: " + author.firstName() + " " + author.lastName());
                System.out.println("---------------------------");
            }
        }
    }
}
