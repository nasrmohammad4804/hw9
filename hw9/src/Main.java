import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> arrayList =new ArrayList<>( Arrays.asList(13,8,2,6,4,9,5,3,1,10,7) ); //2,6 4,9 1,10


        System.out.println("before delete not suitable pair");
        System.out.println(arrayList);
        pair(arrayList);
        System.out.println("after pairing ");
        System.out.println(arrayList);

    }

    public static void pair(ArrayList<Integer> array) {
        if (array.isEmpty()) {
            System.out.println("array is empty");
            return;
        }
        if (array.size() % 2 != 0) {
            array.remove(array.size() - 1);
        }

        for (int i = 0; i < array.size(); i += 2) {

            if (array.get(i) > array.get(i + 1)) {
                array.remove(i);
                array.remove(i );
                i-=2;
            }
        }
    }
}
