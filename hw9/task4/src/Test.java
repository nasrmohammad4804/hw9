
import java.util.ArrayList;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        ArrayList<Person> peoples = new ArrayList<>();
        peoples.add(new Person("Tom", "Wanger"));
        peoples.add(new Person("Elvis", "Presley"));
        peoples.add(new Person("Priscilla", "Wagner"));


        Collections.sort(peoples);

        for (Person person : peoples)
            System.out.println(person);

    }
}
