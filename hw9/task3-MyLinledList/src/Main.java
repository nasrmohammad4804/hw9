import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList=new MyLinkedList<>();

        myLinkedList.add("ali");
        myLinkedList.add("javad");
        myLinkedList.add("zahra");
        myLinkedList.add("hosein");

        for(int i=0; i< myLinkedList.size(); i++){
            System.out.println(myLinkedList.get(i));
        }

        myLinkedList.set(3,"aida");
        myLinkedList.set(1,"naghi");
        // myLinkedList.set(7,"nazanin");
        System.out.println("--------------------------------");
        for(String data : myLinkedList)
            System.out.println(data);

        System.out.println("------------------------");

        myLinkedList.add(4,"mona");

        for(String str : myLinkedList)
            System.out.println(str);

        System.out.println("-----------------------");

        ArrayList<String> arr=new ArrayList<>();
        arr.add("akbar");
        arr.add("ahmad");

        myLinkedList.addAll(arr);

        for(String str : myLinkedList)
            System.out.println(str);


      /* System.out.println("size  : "+myLinkedList.size() );


        for(String data : myLinkedList)
            System.out.println(data);
        System.out.println( "-----------------------------------------------");
        System.out.println(myLinkedList.isEmpty());

        myLinkedList.remove("javad");

        for(String data : myLinkedList)
            System.out.println(data);


        myLinkedList.addFirst("mohadese");


        System.out.println("------------------------------");
        for(String str : myLinkedList)
            System.out.println(str);




        myLinkedList.add(2,"majid");
        System.out.println("-----------------------------");

        for(String str : myLinkedList)
            System.out.println(str);*/


    }
}