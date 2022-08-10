package com.example.demo.sevice.imple;

import java.util.ArrayList;
import java.util.HashMap;

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
    public String setIP(ArrayList<HashMap<String, String>> res){
        res.forEach(i ->{
            mapper.setIP(i);
        });
        return "insert";
    }
    @Override
    public String delIP(HashMap<String, String> del){
            mapper.delIP(del);
        return "insert";
    }

    @Override
    public String update(ArrayList<HashMap<String , String>> params) {
        /*{1920309215={lat=35.564827236310855, lng=129.35910760905102}, 1920309214={lat=35.55205331276655, lng=129.3451051449107}, 1920309213={lat=35.55889493201912, lng=129.3583602726868}, 1920309212={lat=35.557710133147225, lng=129.33591937071702}}*/
        // HASHMAP
        //  {
        //    BSTA_ID : '1920309215',
        //   LAT : 35.564827236310855
        //   LNG : 129.35910760905102
        //   }
        // mapper.update(obj);

        // 키를 빼와서 안의 값들을 Hashmap에다가 넣어준다. 키도 hashmap에 넣어준다.
        // for(String a : key){
        //     String BSTA_ID = a.toString();
        //     String LAT = param.get(BSTA_ID);
        //     System.out.println(LAT);
        // }
        
        // 기본 for문
        // for(int i=0; i<params.size(); i++){
        //     HashMap<String, String> key = params.get(i);
        //     System.out.println(key);
        //     mapper.update(key);
        // }
        
        // forEach
        // for(HashMap<String, String> i : params){
        //     mapper.update(i);
        // }

        params.forEach(i ->{
            mapper.update(i);
        });

        return "test";
    }
}
