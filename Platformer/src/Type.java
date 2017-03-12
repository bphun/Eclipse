public enum Type {
	SQUARE(20,20, 4),
	RECTANGLE(400,20, 3);

	public final double width;
	public final double height;
	public final int numSides;
	
	private Type(double width, double height, int numSides) {
		this.width = width;
		this.height = height;
		this.numSides = numSides;
	}
};