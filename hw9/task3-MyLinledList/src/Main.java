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

        System.out.println("after set\n\n");
        myLinkedList.set(3,"aida");
        myLinkedList.set(1,"naghi");
        // myLinkedList.set(7,"nazanin");
        System.out.println("--------------------------------");
        for(String data : myLinkedList)
            System.out.println(data);

        System.out.println("------------------------");

        myLinkedList.add(4,"mona");
        System.out.println("after add last\n");

        for(String str : myLinkedList)
            System.out.println(str);

        System.out.println("-----------------------");

        ArrayList<String> arr=new ArrayList<>();
        arr.add("akbar");
        arr.add("ahmad");

        myLinkedList.addAll(arr);

        System.out.println("after addAll\n");
        for(String str : myLinkedList)
            System.out.println(str);

        System.out.println("-------------------------------------------");

        System.out.println("get First\n");
        System.out.println(myLinkedList.getFirst());
        System.out.println("getLast\n");
        System.out.println(myLinkedList.getLast());
        System.out.println("to Array\n\n ");
        for(Object object : myLinkedList.toArray())
            System.out.println(object);


        System.out.println("***********************************************************");

        System.out.println(myLinkedList.contains("mansoor"));
        System.out.println(myLinkedList.contains("taghi"));
        System.out.println(myLinkedList.contains("ali"));
        System.out.println(myLinkedList.contains("akbar"));
        System.out.println(myLinkedList.contains("fateme"));

        System.out.println("###############################################");

        myLinkedList.clear();

        System.out.println("size : "+myLinkedList.size());

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