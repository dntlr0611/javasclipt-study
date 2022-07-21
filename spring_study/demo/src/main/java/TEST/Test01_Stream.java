package TEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Test01_Stream{
    private static HashMap<String, ArrayList<String>> map = new HashMap<>();

    public static void main(String[] args){
        
        String[] data = new String[]{"이건,010-5042-2100","이건,010-5042-2200","조우식,010-4184-4212","송유정,010-5042-2300"};
        Stream<String> stream = Arrays.stream(data);
        Stream.of(data).forEach(d -> {
            
        });
        System.out.println(stream);
        stream.forEach(a ->{
            String name = a.split(",")[0];
            String number = a.split(",")[1];
            add(name, number);
        });
        System.out.println(map);
        System.out.println(map.keySet());
        System.out.println(map.get("이건"));
    }
     public static void add(String name, String number){
        if(map.containsKey(name)){
            map.get(name).add(number);
        }else{
            ArrayList<String> dataNumber = new ArrayList<>();
            dataNumber.add(number);
            map.put(name, dataNumber);
        }
    }
}


