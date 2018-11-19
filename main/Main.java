package main;

import java.io.IOException;

import metro.Metro;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Metro metro = new Metro();
		
		metro.readConsumers();
		metro.readSuppliers();
		metro.readFromFile();
		
		metro.createConsumer("catalinho97", "satraefamilia", 0);
		metro.createConsumer("ageorgikC4", "suntsmecher", -1);
		metro.createConsumer("RaresLulaku", "ruplafifa06", 0);
		metro.createConsumer("soarehomosapiens", "paroladesteapta", -1.1f);
		
		metro.changePassword(metro.getConsumer("ageorgikC4"), "totsmechersunt");
		
		metro.createSupplier("metrobot666", "%!@#%!@#", "123459", 50.0f, 99);
		metro.createSupplier("toniPericulosul", "avocatulinimiitale", "RO55631", 51.0f, 13);
		
		metro.logIn(metro.getConsumer("catalinho97"));
		metro.buy("catalinho97", "scovergi", 5, 20, "metrobot666");
		metro.buy("catalinho97", "scovergi", 15, 35, "toniPericulosul");
		metro.logOut(metro.getConsumer("catalinho97"));
		
		metro.logIn(metro.getConsumer("RaresLulaku"));
		metro.buy("RaresLulaku", "catalineala", 0.2f, 0.1f, "metrobot666");
		metro.logOut(metro.getConsumer("RaresLulaku"));
		
		metro.logIn(metro.getSupplier("metrobot666"));
		metro.offer("metrobot666", "fieratanii", 785, 784.9f);
		metro.logOut(metro.getSupplier("metrobot666"));
		
		metro.logIn(metro.getSupplier("toniPericulosul"));
		metro.offer("toniPericulosul", "vrajeala", 1000, 400);
		metro.logOut(metro.getSupplier("toniPericulosul"));
		
		metro.acceptOrDenyOffer(true, metro.getSupplier("toniPericulosul").offers.get(0));
		
		metro.logIn(metro.getConsumer("soarehomosapiens"));
		metro.logOut(metro.getConsumer("soarehomosapiens"));
		
		metro.logIn(metro.getConsumer("soarehomosapiens"));
		metro.logOut(metro.getConsumer("soarehomosapiens"));
		
		metro.logIn(metro.getConsumer("catalinho97"));
		metro.review(metro.getConsumer("catalinho97"), metro.getConsumer("catalinho97").shoppings.get(0));
		metro.logOut(metro.getConsumer("catalinho97"));
		
		metro.logIn(metro.getConsumer("soarehomosapiens"));
		metro.win(metro.getConsumer("soarehomosapiens"));
		metro.logOut(metro.getConsumer("soarehomosapiens"));
		
		metro.logIn(metro.getConsumer("catalinho97"));
		metro.logOut(metro.getConsumer("catalinho97"));
		
		metro.checkSuppliers();
		
		metro.writeConsumers();
		metro.writeSuppliers();
		metro.writeToFile();
	}

}
