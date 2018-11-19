package metro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import helpers.Offers;
import helpers.Shoppings;
import users.Consumer;
import users.Supplier;
import users.User;

public class Metro {

	public ArrayList<Consumer> consumers = new ArrayList<>();
	public ArrayList<Supplier> suppliers = new ArrayList<>();
	PrintWriter supplierWriter, consumerWriter;
	Scanner supplierScanner, consumerScanner;
	
	Scanner shoppingScanner, offerScanner;
	PrintWriter offerWriter, shoppingWriter;
	BufferedWriter bw = null;
	FileWriter fw = null;
	
	public Metro() {
		
	}
	
	public Boolean createConsumer(final String username, final String password, final float coins) throws FileNotFoundException {
		if (!consumerExists(username)) {
			consumers.add(new Consumer(username, password, coins));
			return true;
		}
		return false;
	}
	
	public Boolean createSupplier(final String username, final String password, final String cui, final float rating, final float coins) throws FileNotFoundException {
		if (!supplierExists(username)) {
			suppliers.add(new Supplier(username, password, cui, rating, coins));
			return true;
		}
		return false;
	}
	
	public Boolean consumerExists(final String username) {
		Iterator<Consumer> iterator = consumers.iterator();
		while (iterator.hasNext()) {
			String currentUsername = ((Consumer) iterator.next()).username;
			if (currentUsername.equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean supplierExists(final String username) {
		Iterator<Supplier> iterator = suppliers.iterator();
		while (iterator.hasNext()) {
			String currentUsername = ((Supplier) iterator.next()).username;
			if (currentUsername.equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public Supplier getSupplier(String username) {
		Iterator<Supplier> iterator = suppliers.iterator();
		while (iterator.hasNext()) {
			Supplier supplier = iterator.next();

			if (supplier.username.equals(username)) {
				return supplier;
			}
		}
		return null;
	}
	
	public Consumer getConsumer(String username) {
		Iterator<Consumer> iterator = consumers.iterator();
		while (iterator.hasNext()) {
			Consumer consumer = iterator.next();
			if (consumer.username.equals(username)) {
				return consumer;
			}
		}
		return null;
	}
	
	public void readSuppliers() throws FileNotFoundException {
		supplierScanner = new Scanner(new File("suppliers.txt"));
		
		while(supplierScanner.hasNext()) {
			suppliers.add(new Supplier(supplierScanner.next(), supplierScanner.next(), supplierScanner.next(),
					supplierScanner.nextFloat(), supplierScanner.nextFloat()));
		}	
		supplierScanner.close();
	}
	
	public void readConsumers() throws FileNotFoundException {
		consumerScanner = new Scanner(new File("consumers.txt"));
		
		while(consumerScanner.hasNext()) {
			consumers.add(new Consumer(consumerScanner.next(), consumerScanner.next(), consumerScanner.nextFloat()));
		}	
		consumerScanner.close();
	}
	
	public void writeSuppliers() throws FileNotFoundException {
		supplierWriter = new PrintWriter(new File("suppliers.txt"));
		Iterator<Supplier> iterator = suppliers.iterator();
		while (iterator.hasNext()) {
			Supplier supplier = iterator.next();
			supplierWriter.write(supplier.username + " " + supplier.password + " " + supplier.cui 
					+ " " + supplier.rating + " " + supplier.coins + "\n");
		}
		supplierWriter.close();
	}
	
	public void writeConsumers() throws FileNotFoundException {
		consumerWriter = new PrintWriter(new File("consumers.txt"));
		Iterator<Consumer> iterator = consumers.iterator();
		while (iterator.hasNext()) {
			Consumer consumer = iterator.next();
			consumerWriter.write(consumer.username + " " + consumer.password + " " + consumer.coins + "\n");
		}
		consumerWriter.close();
	}

	public void readFromFile() throws FileNotFoundException {
		Iterator<Supplier> supplier = suppliers.iterator();
		Iterator<Consumer> consumer = consumers.iterator();
		
		while (supplier.hasNext()) {
			Supplier aux = supplier.next();
			readFromFileSupplier(aux.username);
		}
		while (consumer.hasNext()) {
			Consumer aux = consumer.next();
			readFromFileConsumer(aux.username);
		}
	}
	
	public void readFromFileConsumer(String consumerName) throws FileNotFoundException {
		
		shoppingScanner = new Scanner(new File("shoppings.txt"));
		
		while(shoppingScanner.hasNext()) {
			Shoppings shopping = new Shoppings(shoppingScanner.next(), shoppingScanner.nextFloat(),
					shoppingScanner.nextFloat(), shoppingScanner.next(), shoppingScanner.next(), shoppingScanner.next());
			System.out.println(shopping.consumerName + " " + consumerName);
			if (shopping.consumerName.equals(consumerName)) {
				this.getConsumer(consumerName).shoppings.add(shopping);
			}
		}
		shoppingScanner.close();
	}
	
	public void readFromFileSupplier(String supplierName) throws FileNotFoundException {
		offerScanner = new Scanner(new File("offers.txt"));
		
		while(offerScanner.hasNext()) {
			Offers offer = new Offers(offerScanner.next(), offerScanner.next(),
					offerScanner.nextFloat(), offerScanner.nextFloat());
			
			if (offer.supplierName.equals(supplierName))
				this.getSupplier(supplierName).offers.add(offer);
		}
		offerScanner.close();
	}

	public void writeToFile() throws IOException {
		Iterator<Supplier> supplier = suppliers.iterator();
		Iterator<Consumer> consumer = consumers.iterator();
		
		while (supplier.hasNext()) {
			Supplier aux = supplier.next();
			
			Set<Offers> hs = new HashSet<>();
			hs.addAll(aux.offers);
			aux.offers.clear();
			aux.offers.addAll(hs);
			
			writeToFileSupplier(aux.username);
		}
		while (consumer.hasNext()) {
			Consumer aux = consumer.next();
			writeToFileConsumer(aux.username);
		}
	}
	
	public void writeToFileSupplier(String supplierName) throws IOException {
		offerWriter = new PrintWriter(new FileWriter("offers.txt", true));
		Iterator<Offers> iterator = this.getSupplier(supplierName).offers.iterator();
		while (iterator.hasNext()) {
			Offers offer = iterator.next();
			offerWriter.write(offer.product + " " + offer.supplierName + " " + offer.quantity +
				" " + offer.price + "\n");
		}
		offerWriter.close();
	}
	
	public void writeToFileConsumer(String consumerName) throws IOException {
		shoppingWriter = new PrintWriter(new FileWriter("shoppings.txt", true));
		Iterator<Shoppings> iterator = this.getConsumer(consumerName).shoppings.iterator();
		while (iterator.hasNext()) {
			Shoppings shopping = iterator.next();
			
			String time = new SimpleDateFormat("yyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			int thisMonth = Integer.parseInt(time.substring(2, 4));
			int thisDay = Integer.parseInt(time.substring(4, 6));
			int initialMonth = Integer.parseInt(shopping.uniqueData.substring(2, 4));
			int initialDay = Integer.parseInt(shopping.uniqueData.substring(4, 6));
		
			if (thisMonth > initialMonth && thisDay > initialDay) {
				continue;
			}
			shoppingWriter.write(shopping.product + " " + shopping.quantity + " " + shopping.price +
					" " + shopping.supplierName + " " +	shopping.consumerName + " " + shopping.uniqueData + "\n");
		}
		shoppingWriter.close();
	}
	
	public void buy(String consumer, String product, float quantity, float price, String supplier) {
		if (!this.getConsumer(consumer).loggedIn) {
			System.out.println("No instance of customer! Please, log in!");
			return;
		}
		String time = new SimpleDateFormat("yyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		this.getConsumer(consumer).shoppings.add(new Shoppings(product, quantity, price, supplier,
				consumer, time.substring(0, 6) + time.substring(7, 13)));
	}
	
	public void offer(String supplierName, String product, float quantity, float price) {
		if (!this.getSupplier(supplierName).loggedIn) {
			System.out.println("No instance of customer! Please, log in!");
			return;
		}
		this.getSupplier(supplierName).offers.add(new Offers(product, supplierName, quantity, price));
	}

	public void changePassword(User user, String password) {
		if (user.loggedIn) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your old password:");
			String oldPassword = scanner.next();
			if (oldPassword.equals(user.password)) {
				System.out.println("Enter your new password:");
				String newPassword = scanner.next();
				user.password = newPassword;
				System.out.println("Password successfully changed!");
			}
			//scanner.close();
		}
	}
	
	public void logIn(User user) {
		if (user.loggedIn == true) {
			System.out.println("You are already logged in!\nPlease, log out to log in!");
			return;
		}
		System.out.println("Enter your password:");
		Scanner scanner = new Scanner(System.in);
		String password = scanner.nextLine();
		
		if (password.equals(user.password)) {
			user.loggedIn = true;
		} else {
			System.out.println("Wrong password!");
		}
		//scanner.close();
	}
	
	public void logOut(User user) {
		user.loggedIn = false;
	}
	
	public void acceptOrDenyOffer(Boolean status, Offers offer) {
		this.getSupplier(offer.supplierName).offers.remove(offer);
	}
	
	public void review(Consumer consumer, Shoppings shopping) {
		
		if (!consumer.loggedIn) {
			System.out.println("No instance of customer! Please, log in!");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Rate your experience! Make the community better for us all!");
		System.out.println("On a scale from 1 to 100, how cheap you considered the products?");
		float cheap = scanner.nextFloat();
		System.out.println("On a scale from 1 to 100, how good were your products?");
		float good = scanner.nextFloat();
		
		float coins = shopping.price / 100;
		System.out.println("Thank you! By helping us, you earned an amout of " + coins + " coins!");
		System.out.println("RATING-UL INAINTE ESTE:" + this.getSupplier(shopping.supplierName).rating);
		this.getSupplier(shopping.supplierName).rating += (((good - 50) * shopping.price) / 500) +
				(((cheap - 50) * shopping.quantity) / 500);
		System.out.println("RATING-UL ESTE: " + this.getSupplier(shopping.supplierName).rating);
		this.getConsumer(shopping.consumerName).coins += coins;
		this.getConsumer(shopping.consumerName).shoppings.remove(shopping);
		
		//scanner.close();
	}
	
	public void win(User user) {
		if (!user.loggedIn) {
			System.out.println("No instance of customer! Please, log in!");
			return;
		}
		System.out.println("If you want to use your existing coins to purchase our surprise, write yes in the console.");
		System.out.println("It requires an amount of coins equal to 3000. Are you sure do you want to proceed?");
		
		Scanner scanner = new Scanner(System.in);
		String confirmation = scanner.next();
	//	scanner.close();
		if (confirmation.equals("yes") && user.coins >= 3000) {
			System.out.println("Congratulations! You just won a 500$ Polo watch! You can take it from our shop!");
			user.coins -= 3000;
			return;
		} else if (user.coins < 3000){
			System.out.println("Not enough coins!");
		} else {
			System.out.println("Take your time and think about it!");
		}
	}

	Comparator<Supplier> ratingComparator = new Comparator<Supplier>() {

		@Override
		public int compare(Supplier one, Supplier two) {

			  if (one.rating > two.rating) {
				  return 1;
			  } else if (one.rating < two.rating) {
				  return -1;
			  } else {
				  float firstCount = 0, secondCount = 0;
				  Iterator<Offers> firstOffers = one.offers.iterator();
				  Iterator<Offers> secondOffers = two.offers.iterator();
				  
				  while (firstOffers.hasNext()) {
					  Offers offer = firstOffers.next();
					  firstCount += offer.quantity / offer.price;
				  }
				  while (secondOffers.hasNext()) {
					  Offers offer = secondOffers.next();
					  secondCount += offer.quantity / offer.price;
				  }
				  if (firstCount > secondCount) return 1;
			  }
			  return -1;
		}
	};
	
	public void checkSuppliers() {
		Collections.sort(suppliers, ratingComparator);
		Iterator<Supplier> iterator = suppliers.iterator();
		while (iterator.hasNext()) {
			Supplier supplier = iterator.next();
			if (supplier.rating < 20) {
				System.out.println(supplier.rating);
				//suppliers.remove(supplier);
			} else if (supplier.rating > 150) {
				supplier.coins += 400;
				supplier.rating = 100;
			}
		}
	}

}
