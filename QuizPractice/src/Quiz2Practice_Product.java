
public class Quiz2Practice_Product {


	private int UPC;
	private double basePrice;
	private double currentPrice; 
	private boolean taxable;
	private String productName;
	private String category;
	private boolean onSale;
	private double discountAmount;
	private String location;

	public Quiz2Practice_Product(int UPC, double basePrice, boolean taxable, String productName, String category, boolean onSale, double discountAmount, String location) {
		this.UPC = UPC;
		this.basePrice = basePrice;
		this.taxable = taxable;
		this.productName = productName;
		this.category = category;
		this.onSale = onSale;
		this.discountAmount = discountAmount;
		this.location = location;	
	}
	
	public int getUPC() {
		return UPC;
	}
	
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
		this.currentPrice = basePrice;
	}
	
	public boolean getTaxble() {
		return taxable;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getCategory() {
		return category;
	}
	
	public boolean getSaleStatus() {
		return onSale;
	}
	public void setSaleStatus(boolean saleStatus) {
		this.onSale = saleStatus;	
		
		if (saleStatus == false) {
			currentPrice = basePrice;
		} else {
			currentPrice = (basePrice * getDiscountAmount()) + basePrice;
		}
		
	}
	
	public double getDiscountAmount() {
		return discountAmount;	
	}
	
	public String getLocationInStore() {
		return location;
	}
}
