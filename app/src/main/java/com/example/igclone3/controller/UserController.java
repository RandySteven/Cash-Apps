package com.example.igclone3.controller;

import com.example.igclone3.model.User;

import java.util.Random;

public class UserController {
    private static String generateId(){
        Random rand = new Random();
        int a1 = rand.nextInt(10);
        int a2 = rand.nextInt(10);
        int a3 = rand.nextInt(10);
        return "US" + a1 + "" + a2 + "" + a3;
    }

    public static User createUser(String userName, String userEmail, String userPassword){
        String userId = generateId();
        return new User(userId, userName, userEmail, userPassword);
    }
}
