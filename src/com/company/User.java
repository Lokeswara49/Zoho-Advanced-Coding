package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User extends Main{
    public int userId;
    String name;
    Set<User> blockedUsers=new HashSet<>();
    //Set<User> friends=new HashSet<>();
    User(int userId,String name){
        this.name=name;
        this.userId=userId;
    }



}
