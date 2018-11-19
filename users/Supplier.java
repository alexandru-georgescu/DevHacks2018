package users;

import java.util.ArrayList;

import helpers.Offers;

public class Supplier extends User{

	public String cui;
	public float rating;
	public ArrayList<Offers> offers = new ArrayList<>();
	
	public Supplier(String username, String password, String cui, float rating, float coins) {
		this.username = username;
		this.password = password;
		this.cui = cui;
		loggedIn = false;
		this.rating = rating;
		this.coins = coins;
	}
	
}
