import java.util.Arrays;

public class PigeonholeSort extends BaseSort{
    public int[] Sort(int[] arr) {
        int n = arr.length;
        int min = arr[0];
        int max = arr[0];

        for (int value : arr) {
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }

        }

        int range = max - min + 1;
        int[] holes = new int[range];
        Arrays.fill(holes,0);

        for (int i = 0; i < n; i++) {
            holes[arr[i]- min]++;
        }
        int index = 0;

        for (int i = 0; i < range; i++) {
            while (holes[i]-- > 0){
                arr[index++] = i + min;
            }
        }
        return arr;
    }
}
