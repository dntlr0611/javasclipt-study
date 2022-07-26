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
}
