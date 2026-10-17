package main;

import java.util.Scanner;
import java.util.ArrayList;
import user.User;

public class Main{
    public static void main(String[] args){
        ArrayList<User> userList = new ArrayList<>();

        boolean loginCheck = false;

        User user1 = new User(1, "Joseph", "joseph@gmail.com", "12345678");

        userList.add(user1);

        System.out.println("=========================================");
        System.out.println(" Welcome to the Event Ticketing System");
        System.out.println("=========================================");

        System.out.println(userList);

        User u = new User();

        String[] logInData = u.logIn();
        System.out.println(logInData);
    }
}