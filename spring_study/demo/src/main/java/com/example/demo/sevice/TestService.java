package com.example.demo.sevice;

import java.util.ArrayList;
import java.util.HashMap;

public interface TestService {
    public ArrayList<HashMap<String, String>> getData();

    public ArrayList<HashMap<String, String>> getIP();

    public ArrayList<HashMap<String, String>> getLink();

    public String update(ArrayList<HashMap<String , String>> params);
    
    // public String setIP(ArrayList<HashMap<String, String>> res);
    
    public String delIP(ArrayList<HashMap<String, String>> del);
}
