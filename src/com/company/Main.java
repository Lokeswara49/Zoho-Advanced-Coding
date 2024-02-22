package com.company;

import java.util.*;

public class Main {
    public static List<Group> GroupList=new ArrayList<>();
    public static List<User> UsersList=new ArrayList<>();
    static Map<String,List<String>> seenMessages = new HashMap<>();
    static Map<String,List<String>> allMessages = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. add_User(String name) ");
        System.out.println("2. block_user(int userId,int blockId) ");
        //System.out.println("3. delete_all_Users()");
        System.out.println("3. sendMessage(int userId1,int userId2,String message,boolean read) ");
        System.out.println("5. viewConversation(int userId1,int userId2) ");
        System.out.println("6. broadCast(int userId,String message) ");
        System.out.println("7. createGroup(int groupId,int adminId) ");
        System.out.println("8. addGroupMembers(int groupId,int userId) ");
        System.out.println("9. deleteGroupParticipants(int groupId,int userId) ");
        System.out.println("10. changeAdmin(int groupId,int adminId) ");
        System.out.println("11. sendMsgToGroup(int groupId,int userId,String message) ");
        System.out.println("12. ListGroupMessages(int groupId)");
        boolean flag=true;
        while(flag) {
            System.out.println("enter choice");
            int n = sc.nextInt();
            String s= sc.nextLine();
            //Main obj = new Main();
            if (n == 1) {
                String s1 = sc.nextLine();
                add_User(s1);
            }
            if (n == 2) {
                int id1 = sc.nextInt();
                int id2 = sc.nextInt();
                block_user(id1, id2);
            }
            if (n == 3) {
                int i1 = sc.nextInt();
                int i2 = sc.nextInt();
                String s2=sc.nextLine();
                String msg = sc.nextLine();
                boolean read = sc.nextBoolean();
                sendMessage(i1, i2, msg);
            }
            if (n == 4) {
                int id1 = sc.nextInt();
                int id2 = sc.nextInt();
                viewConversation(id1, id2);
            }
            if (n == 5) {
                int id = sc.nextInt();
                String s2=sc.nextLine();
                String msg = sc.nextLine();
                broadCast(id, msg);
            }
            if (n == 7) {
                int gid = sc.nextInt();
                int adminid = sc.nextInt();
                createGroup(gid, adminid);
            }
            if(n==8){
                int gid = sc.nextInt();
                int adminid = sc.nextInt();
                addGroupMembers(gid,adminid);
            }
            if(n==9){
                int gid = sc.nextInt();
                int adminid = sc.nextInt();
                deleteGroupParticipants(gid,adminid);
            }
            if(n==10){
                int gid = sc.nextInt();
                int adminid = sc.nextInt();
                changeAdmin(gid,adminid);
            }
            if (n == 11) {
                int gid = sc.nextInt();
                int id = sc.nextInt();
                String s2=sc.nextLine();
                String msg = sc.nextLine();
                sendMsgToGroup(gid, id, msg);
        }
            if(n==12){
                int gid= sc.nextInt();
                ListGroupMessages(gid);
            }

        }

    }
    public static void add_User(String name){
        int userId=UsersList.size();
        User user=new User(userId,name);
        UsersList.add(user);
        //System.out.println(UsersList.get(UsersList.size()-1).name);
    }
    public static void block_user(int userId,int blockId){
        User user = null;
        for(int i=0;i<UsersList.size();i++){
            if(UsersList.get(i).userId==userId){
                user=UsersList.get(i);
                break;
            }
        }
        User blockuser=null;
        for(int i=0;i<UsersList.size();i++){
            if(UsersList.get(i).userId==blockId){
                blockuser=UsersList.get(i);
                break;
            }
        }
        if(user!=null)
            user.blockedUsers.add(blockuser);
    }
    public static void delete_all_Users(){
        UsersList.clear();
    }
    public static void sendMessage(int userId1,int userId2,String message){
        User user1=null;
        User user2=null;
        for(int i=0;i< UsersList.size();i++){
            if(UsersList.get(i).userId==userId1){
                user1=UsersList.get(i);
            }
            if(UsersList.get(i).userId==userId2){
                user2=UsersList.get(i);
            }
        }
        //System.out.println(user1.name+" "+user2.name);
        if(!user1.blockedUsers.contains(user2)) {
            String key = user1.userId+","+user2.userId;
            message = user1.name + ": " + message;

                List<String> all = new ArrayList<>();
                if(seenMessages.containsKey(key)){
                    all=seenMessages.get(key);
                }
                all.add(message);
                seenMessages.put(key, all);
                List<String> all2 = new ArrayList<>();
                if(allMessages.containsKey(key)){
                    all2=allMessages.get(key);
                }
                all2.add(message);
                allMessages.put(key, all2);


//                List<String> all = allMessages.get(key);
//                all.add(message);
//                allMessages.put(key, all);
        }
    }
    public static void viewConversation(int userId1,int userId2){
        User user1=null;
        User user2=null;
        for(int i=0;i< UsersList.size();i++){
            if(UsersList.get(i).userId==userId1){
                user1=UsersList.get(i);
            }
            if(UsersList.get(i).userId==userId2){
                user2=UsersList.get(i);
            }
        }
        String key=user1.userId+","+user2.userId;
        //System.out.println("all"+allMessages);
        List<String> ans=allMessages.get(key);
        for(int i=0;i< ans.size();i++){
            System.out.println(ans.get(i));
        }
    }
    public static void broadCast(int userId,String message){
        User user=null;
        for(int i=0;i< UsersList.size();i++) {
            if (UsersList.get(i).userId == userId) {
                user = UsersList.get(i);
            }
        }
        for(int i=0;i<UsersList.size();i++){
            if(user.userId!=UsersList.get(i).userId)
                sendMessage(user.userId,UsersList.get(i).userId,message);
        }
    }
    public static void createGroup(int groupId,int adminId){
        Group group=new Group(adminId,groupId);
        GroupList.add(group);
        group.groupPartipants.add(adminId);
        System.out.println(GroupList.get(GroupList.size()-1).groupId);
    }

    public static void addGroupMembers(int groupId,int userId){
        Group group=null;
        for(int i=0;i<GroupList.size();i++){
            if(GroupList.get(i).groupId==groupId){
                group=GroupList.get(i);
                break;
            }
        }
        group.groupPartipants.add(userId);
        System.out.println(group.groupPartipants);
    }
    public  static void deleteGroupParticipants(int groupId,int userId){
        Group group=null;
        for(int i=0;i<GroupList.size();i++){
            if(GroupList.get(i).groupId==groupId){
                group=GroupList.get(i);
                break;
            }
        }
        group.groupPartipants.remove(userId);
        if(group.adminId==userId){
            group.adminId=0;
        }
        System.out.println(group.groupPartipants);
    }
    public static void changeAdmin(int groupId,int adminId){
        Group group=null;
        for(int i=0;i<GroupList.size();i++){
            if(GroupList.get(i).groupId==groupId){
                group=GroupList.get(i);
                break;
            }
        }
        group.adminId=adminId;
        System.out.println("admin"+ group.adminId);
    }
    public static void sendMsgToGroup(int groupId,int userId,String message){
        Group group=null;
        for(int i=0;i<GroupList.size();i++){
            if(GroupList.get(i).groupId==groupId){
                group=GroupList.get(i);
                break;
            }
        }
        if(group.groupPartipants.contains(userId)) {
            message = userId + ": " + message;
            group.groupMessages.add(message);
        }
    }
    public static void ListGroupMessages(int groupId){
        Group group=null;
        for(int i=0;i<GroupList.size();i++){
            if(GroupList.get(i).groupId==groupId){
                System.out.println("Hello");
                group=GroupList.get(i);
                break;
            }
        }
        for(int i = 0; i<group.groupMessages.size(); i++){
            System.out.println(group.groupMessages.get(i));
        }
    }

}


