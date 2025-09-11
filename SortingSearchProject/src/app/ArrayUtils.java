package app;

public class ArrayUtils {


    public static void mergeSort(int[] array) {
        if (array.length < 2) return;

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];


        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);


        mergeSort(left);
        mergeSort(right);


        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;


        while (i < left.length && j < right.length) {
            array[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }


        while (i < left.length) array[k++] = left[i++];
        while (j < right.length) array[k++] = right[j++];
    }


    public static int binarySearch(int[] array, int target) {
        int low = 0, high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) return mid;
            else if (array[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }
}
