package com.bitbuy.JavaTestApp.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bitbuy.JavaTestApp.model.User;
import com.bitbuy.JavaTestApp.model.UserCredentials;

@Service
public class UserService {
	
	//Using List of objects(instead of DB) as DataSource for simplicity. 
	List<UserCredentials> usersCredentialsList= new ArrayList<UserCredentials>();
	List<User> users= new ArrayList<User>();
	
	public boolean registerUser(UserCredentials userCredentials) {
		usersCredentialsList.add(userCredentials);
		return usersCredentialsList.contains(userCredentials);
	}
	
	public boolean loginUser(UserCredentials userCredentials) {
		return usersCredentialsList.contains(userCredentials);
	}

	public User getUserByUuid(String uuid) {
		// TODO Auto-generated method stub
		try {
			return users.stream()
						.filter(u->u.getUuid().equals(uuid))
						.findAny()
						.get();
		} catch(NoSuchElementException e) {
			System.out.println("No user data found");
			return null;
		}
	}
	
	public String addOrUpdateUserData(User user) {
		
		Optional<User> userObject= users.stream()
										.filter(u->u.getUuid().equals(user.getUuid()))
										.findFirst();
		if(userObject.isEmpty()) {
			users.add(user);
			return "User Added";
		}else {
			userObject.ifPresent(u->{
				u.setName(user.getName());
				u.setEmail(user.getEmail());
				u.setPhone(user.getPhone());
			});
			return "User Updated";
		}
	}
}
