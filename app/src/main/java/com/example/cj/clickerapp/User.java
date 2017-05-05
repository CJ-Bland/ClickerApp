package com.example.cj.clickerapp;

import java.util.ArrayList;

/**
 * Created by CJ on 2/23/2017.
 * Was used when using my proto database. Since using Facebook login, class is irrelevant. Will be
 * deleted later
 *
 * @author CJ Bland
 * @version 1.0
 */
public class User {

    public long userId;
    public String username;
    public String password;
    public ArrayList<String> classes;

    public User(long id, String uName, String pword){
        this.userId = id;
        this.username = uName;
        this.password = pword;
        this.classes = new ArrayList<>();
    }

    public void addClass(String newClass){
        classes.add(newClass);
    }
}
