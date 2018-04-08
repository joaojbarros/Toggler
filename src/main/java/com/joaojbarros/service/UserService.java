package com.joaojbarros.service;

import com.joaojbarros.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
