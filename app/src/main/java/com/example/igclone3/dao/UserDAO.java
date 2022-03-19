package com.example.igclone3.dao;

import com.example.igclone3.model.User;

public interface UserDAO {
    public String registerUser(User user);
    public User loginUser(String email, String password);
}
