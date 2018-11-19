package users;

import java.util.ArrayList;

import helpers.Shoppings;

public class Consumer extends User{

	public ArrayList<Shoppings> shoppings = new ArrayList<>();
	
	public Consumer(String username, String password, float coins) {
		this.username = username;
		this.password = password;
		this.coins = coins;
		loggedIn = false;
	}
	
}
