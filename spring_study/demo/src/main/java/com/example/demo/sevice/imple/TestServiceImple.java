package com.example.demo.sevice.imple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.TestMapper;
import com.example.demo.sevice.TestService;

@Service("TestService")

public class TestServiceImple implements TestService {
    @Autowired
    TestMapper mapper;

    @Override
    public ArrayList<HashMap<String, String>> getData() {
        return mapper.getData();
    }

    @Override
    public ArrayList<HashMap<String, String>> getIP() {
        return mapper.getIP();
    };

    @Override
    public ArrayList<HashMap<String, String>> getLink() {
        return mapper.getLink();
    };
    @Override
    public int update(HashMap<String, Object> param) {
        /*{1920309215={lat=35.564827236310855, lng=129.35910760905102}, 1920309214={lat=35.55205331276655, lng=129.3451051449107}, 1920309213={lat=35.55889493201912, lng=129.3583602726868}, 1920309212={lat=35.557710133147225, lng=129.33591937071702}}*/
        // HASHMAP
        //  {
        //    BSTA_ID : '1920309215',
        //   LAT : 35.564827236310855
        //   LNG : 129.35910760905102
        //   }
        // mapper.update(obj);
        
        // 키를 빼와서 안의 값들을 Hashmap에다가 넣어준다. 키도 hashmap에 넣어준다.
        Set<String> key = param.keySet();
        // for(String a : key){
        //     String BSTA_ID = a.toString();
        //     String LAT = param.get(BSTA_ID);
        //     System.out.println(LAT);
        // }
        for(Iterator<String> itr = key.iterator(); itr.hasNext();){
            HashMap<String, String> object = new HashMap<>();
            String bstaId = itr.next();
            HashMap<String, Double> pos = (HashMap<String, Double>) param.get(bstaId);
            Double lat = pos.get("lat");
            Double lng = pos.get("lng");
            object.put("BSTA_ID", bstaId.toString());
            object.put("LAT", lat.toString());
            object.put("LNG", lng.toString());
            System.out.println(object);
            mapper.update(object);
        }
        return 1;
    }
}
