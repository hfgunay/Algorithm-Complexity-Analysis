public class CountingSort extends BaseSort{

    public int[] Sort(int[] arr) {
        int k = findMax(arr);
        int[] count = new int[k +1];
        int[] output = new int[arr.length];

        int size = arr.length;
        for(int i = 0; i < size; i++) {
            int j= arr[i];
            count[j] = count[j] + 1;
        }
        for(int i=1; i < k+1;i++) {
            count[i] = count[i] + count[i-1];
        }
        for (int i = size-1; i >=0; i--) {
            int j = arr[i];
            count[j] = count[j] -1;
            output[count[j]] = arr[i];
        }
        return output;

    }

    public int findMax(int [] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];

        }
        return max;
    }


    }

