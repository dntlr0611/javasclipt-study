package com.example.demo.sevice;

import java.util.ArrayList;
import java.util.HashMap;

public interface TestService {
    public ArrayList<HashMap<String, String>> getData();

    public ArrayList<HashMap<String, String>> getIP();

    public ArrayList<HashMap<String, String>> getLink();
}
