import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
        System.out.println(overlapN);
        // 하지만 같은 값이 나온 이유는 

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


