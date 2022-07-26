package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    public ArrayList<HashMap<String, String>> getData();
}
