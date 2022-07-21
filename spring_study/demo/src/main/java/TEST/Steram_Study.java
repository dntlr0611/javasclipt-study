package TEST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public class Steram_Study {
    private static HashMap<String, ArrayList<String>> map = new HashMap<>();
    
    public static void main(String[] args){
        
        String[] data = {"이건, 010-5024-2100","이건, 010-5021-2200","조우식, 010-4184-4212", "송유정, 010-5024-2230"};
        Stream.of(data).forEach(d ->{
            String name = d.split(",")[0];
            String number = d.split(",")[1];
            add(name, number);

        });
        System.out.println(map.keySet());
        Set<String> overlapN = map.keySet();
        Iterator<String> itr = overlapN.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
    static void add(String name, String number){
        if(map.containsKey(name)){
            map.get(name).add(number);
        }else{
            ArrayList<String> dataset = new ArrayList<>();
            dataset.add(number);
            map.put(name, dataset);
        }
    }
}
