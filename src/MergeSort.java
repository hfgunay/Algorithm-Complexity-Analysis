import java.util.ArrayList;

public class MergeSort{

    public static ArrayList<Integer> Sort(ArrayList<Integer> arrayList) {
        int n = arrayList.size();
        if (n <= 1) {
            return arrayList;
        }
        ArrayList<Integer> left = new ArrayList<>(arrayList.subList(0, n / 2));
        ArrayList<Integer> right = new ArrayList<>(arrayList.subList(n / 2, n));
        left = Sort(left);
        right = Sort(right);
        return merge(left, right);
    }


    public static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> resultArrayList = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.get(0) < right.get(0)) {
                resultArrayList.add(left.get(0));
                left.remove(0);
            }
            else {
                resultArrayList.add(right.get(0));
                right.remove(0);
            }
        }
        while (!left.isEmpty()) {
            resultArrayList.add(left.get(0));
            left.remove(0);
        }
        while (!right.isEmpty()) {
            resultArrayList.add(right.get(0));
            right.remove(0);
        }
        return resultArrayList;
    }






}
