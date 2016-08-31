package lab01;

import lab01.*;

public class Pump {
	private final int QTY_MAX = 50000, PRICE_MAX = 100, PRICE_MIN = 1;
	private double galQty, galPrice;
	private int type;

	public Pump() {
		type = 0;
		galQty = 0;
		galPrice = 0;
	}

	public Pump(int type) {
		this.type = type;
		galQty = 0;
		galPrice = 0;
	}

	public Pump(int type, double galPrice) {
		this.type = type;
		galQty = 0;
		this.galPrice = galPrice;
	}

	public Pump(int type, double galQty, double galPrice) {
		this.type = type;
		this.galQty = galQty;
		this.galPrice = galPrice;
	}

	public void setGalQty() {
		galQty = 0;
	}

	public void setGalPrice(double galPrice) throws PriceException {
		if(galPrice > PRICE_MAX || galPrice < PRICE_MIN)
			throw new PriceException();
		this.galPrice = galPrice;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getGalQty() {
		return galQty;
	}

	public double getGalPrice() {
		return galPrice;
	}

	public String getType() {
		switch(type) {
			case 0:
				return "Regular";
			case 1:
				return "Plus";
			case 2:
				return "Super";
			default:
				return null;
		}
	}

	public void modGalQty(double galQty) throws QtyException {
		if(this.galQty + galQty > QTY_MAX)
			throw new QtyException();
		this.galQty += galQty;
	}

	public double sell(double galQty) {
		double total;

		if(this.galQty - galQty < 0)
			galQty = this.galQty;
		this.galQty -= galQty;

		total = galQty * galPrice;

		return total;
	}
}