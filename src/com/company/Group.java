package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {
    int groupId;
    String name;
    int adminId=-1;
    Set<Integer> groupPartipants =new HashSet<>();
    List<String> groupMessages =new ArrayList<>();
    Group(int adminId,int groupId){
        this.adminId=adminId;
        this.groupId=groupId;

    }
}
