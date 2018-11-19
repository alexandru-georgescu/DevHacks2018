package helpers;

public class Shoppings {

	public String supplierName;
	public String consumerName;
	public String product;
	public float quantity;
	public float price;
	public String uniqueData;
	
	public Shoppings(String product, float quantity, float price, String supplierName,
			String consumerName, String uniqueData) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.supplierName = supplierName;
		this.consumerName = consumerName;
		this.uniqueData = uniqueData;
	}
	
}
