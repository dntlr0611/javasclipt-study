package TEST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

public class Test01{
    private static HashMap<String, ArrayList<String>> map = new HashMap<>();

    public static void main(String[] args){
        String[] data = {"이건,010-5042-2100","이건,010-5042-2200","조우식,010-4184-4212","송유정,010-5042-2300"};
        for(String temp : data){
            String name = temp.split(",")[0];
            String number = temp.split(",")[1];
            // 전화번호부 Add
            add(name, number);
        }
        // 전화번호부의 이름 목록 출력.
        
        System.out.println(map.keySet());
        // 중복 비허용.

        Set<String> overlapN = map.keySet();
        Iterator<String> itr = overlapN.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        for(Iterator<String> itr2 = overlapN.iterator(); itr2.hasNext();){
            System.out.println(itr2.next());
        }
        
        overlapN.stream().forEach(d -> {
            System.out.println(d);
        });

        System.out.println(overlapN);
        // 하지만 같은 값이 나온 이유는 add할때 중복 값 처리.
        System.out.println(map);

    }
    public static void add(String name, String number){
        if(map.containsKey(name)){
            map.get(name).add(number);
        }else{
            ArrayList<String> dataset = new ArrayList<>();
            dataset.add(number);
            map.put(name, dataset);
        }
    }
}


