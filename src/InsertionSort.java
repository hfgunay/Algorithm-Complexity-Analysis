

public class InsertionSort extends BaseSort {

    public int[] Sort(int[] array) {
        int arrayLength = array.length;

        for (int j = 1; j < arrayLength; j++) {
            int key = array[j];
            int i = j - 1;

            while (i >= 0 && array[i] > key) {
                array[i+1] = array[i];
                i = i - 1;
            }
            array[i + 1] = key;
        }
        return array;

    }

}
