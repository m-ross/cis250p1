/*	Program Name:	Petrol Sales
	Programmer:		Marcus Ross
	Date Due:		31 Jan 2014
	Description:	This program simulates an array of three petrol pumps, keeping track of each's quantity of gallons of fuel, price per gallon, and type of fuel. To prevent clerical errors, the pump object has constants that define the maximum quantity of the fuel store, and the maximum and minimum prices of the foreseeable future.	*/

package lab01;

import lab01.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		new Win();
	}
}

class Win extends Frame implements WindowListener {
	private Pump[] pump;
	private int pumpSelect; // this picks which pump will be operated on

	public Win() {
		pump = new Pump[3]; // three pumps
		pump[0] = new Pump(0, 4.012); // arbitrary initial prices
		pump[1] = new Pump(1, 4.345);
		pump[2] = new Pump(2, 4.678);

		pumpSelect = 0; // first pump arbitrarily selected

		setSize(500, 140);
		setResizable(false);
		setTitle("Petrol Pump Control");
		addWindowListener(this);
		setLocationRelativeTo(null);
		load(); // this is the function that returns arrangement to default
	}

	private void load() {
		removeAll();
		SpringLayout layout = new SpringLayout(); // component initializations
		SpringLayout statusLyt0 = new SpringLayout();
		SpringLayout statusLyt1 = new SpringLayout();
		SpringLayout statusLyt2 = new SpringLayout();
		Panel panel = new Panel(layout);
		Panel menu = new Panel(new GridLayout(4, 1));
		Panel status = new Panel(new GridLayout(1, 3));
		Panel status0 = new Panel(statusLyt0);
		Panel status1 = new Panel(statusLyt1);
		Panel status2 = new Panel(statusLyt2);
		Panel select = new Panel(new GridLayout(1,3));

		// four main menu buttons
		Button btnSetPrice = new Button("Set Price Per Gallon");
		Button btnSetQty = new Button("Reset Fuel Quantity");
		Button btnAddGal = new Button("Add Gallons");
		Button btnSell = new Button("Sell Fuel");

		// components identified with "0" relate to the first pump, and so on--I'm a bit confused in retrospect over why I didn't make arrays of labels and textfields
		Label type0 = new Label(pump[0].getType(), Label.CENTER);
		TextField qty0 = new TextField(String.format("%.2f",pump[0].getGalQty()));
		TextField price0 = new TextField(String.format("$%.3f",pump[0].getGalPrice()));
		Label gal0 = new Label("gal");
		Label usd0 = new Label("USD");
		Label type1 = new Label(pump[1].getType(), Label.CENTER);
		TextField qty1 = new TextField(String.format("%.2f",pump[1].getGalQty()));
		TextField price1 = new TextField(String.format("$%.3f",pump[1].getGalPrice()));
		Label gal1 = new Label("gal");
		Label usd1 = new Label("USD");
		Label type2 = new Label(pump[2].getType(), Label.CENTER);
		TextField qty2 = new TextField(String.format("%.2f",pump[2].getGalQty()));
		TextField price2 = new TextField(String.format("$%.3f",pump[2].getGalPrice()));
		Label gal2 = new Label("gal");
		Label usd2 = new Label("USD");
		Button btnSelect0 = new Button("Select Pump 0");
		Button btnSelect1 = new Button("Select Pump 1");
		Button btnSelect2 = new Button("Select Pump 2");

		qty0.setEditable(false);	qty1.setEditable(false);	qty2.setEditable(false);
		price0.setEditable(false);	price1.setEditable(false);	price2.setEditable(false);

		btnSetPrice.addActionListener(new setPrice(price0, price1, price2, menu));
		btnSetQty.addActionListener(new setQty(qty0, qty1, qty2));
		btnAddGal.addActionListener(new addGal(qty0, qty1, qty2, menu));
		btnSell.addActionListener(new sell(qty0, qty1, qty2, menu));
		btnSelect0.addActionListener(new Select(status0, status1, status2));
		btnSelect1.addActionListener(new Select(status0, status1, status2));
		btnSelect2.addActionListener(new Select(status0, status1, status2));

		menu.add(btnSetPrice);	menu.add(btnSetQty);	menu.add(btnAddGal);	menu.add(btnSell);

		status0.add(type0);		status0.add(qty0);	status0.add(price0);	status0.add(gal0);
		status0.add(usd0);		status1.add(type1);	status1.add(qty1);		status1.add(price1);
		status1.add(gal1);		status1.add(usd1);	status2.add(type2);		status2.add(qty2);
		status2.add(price2);	status2.add(gal2);	status2.add(usd2);

		select.add(btnSelect0);	select.add(btnSelect1);	select.add(btnSelect2);

		status.add(status0);	status.add(status1);	status.add(status2);

		panel.add(menu);	panel.add(status);	panel.add(select);	add(panel);

		// aw yiss--constraints
		statusLyt0.putConstraint("HorizontalCenter", qty0, 0, "HorizontalCenter", status0);
		statusLyt0.putConstraint("West", qty0, 10, "West", status0);
		statusLyt0.putConstraint("East", qty0, 0, "West", gal0);
		statusLyt0.putConstraint("West", price0, 0, "West", qty0);
		statusLyt0.putConstraint("East", price0, 0, "West", usd0);
		statusLyt0.putConstraint("West", type0, 0, "West", qty0);
		statusLyt0.putConstraint("East", type0, 0, "East", gal0);
		statusLyt0.putConstraint("VerticalCenter", qty0, 0, "VerticalCenter", status0);
		statusLyt0.putConstraint("South", type0, 0, "North", qty0);
		statusLyt0.putConstraint("North", price0, 0, "South", qty0);
		statusLyt0.putConstraint("VerticalCenter", gal0, 0, "VerticalCenter", status0);
		statusLyt0.putConstraint("East", gal0, -10, "East", status0);
		statusLyt0.putConstraint("North", usd0, 0, "South", qty0);
		statusLyt0.putConstraint("East", usd0, -10, "East", status0);
		statusLyt1.putConstraint("HorizontalCenter", qty1, 0, "HorizontalCenter", status1);
		statusLyt1.putConstraint("West", qty1, 10, "West", status1);
		statusLyt1.putConstraint("East", qty1, 0, "West", gal1);
		statusLyt1.putConstraint("West", price1, 0, "West", qty1);
		statusLyt1.putConstraint("East", price1, 0, "West", usd1);
		statusLyt1.putConstraint("West", type1, 0, "West", qty1);
		statusLyt1.putConstraint("East", type1, 0, "East", gal1);
		statusLyt1.putConstraint("VerticalCenter", qty1, 0, "VerticalCenter", status1);
		statusLyt1.putConstraint("South", type1, 0, "North", qty1);
		statusLyt1.putConstraint("North", price1, 0, "South", qty1);
		statusLyt1.putConstraint("VerticalCenter", gal1, 0, "VerticalCenter", status1);
		statusLyt1.putConstraint("East", gal1, -10, "East", status1);
		statusLyt1.putConstraint("North", usd1, 0, "South", qty1);
		statusLyt1.putConstraint("East", usd1, -10, "East", status1);
		statusLyt2.putConstraint("HorizontalCenter", qty2, 0, "HorizontalCenter", status2);
		statusLyt2.putConstraint("West", qty2, 10, "West", status2);
		statusLyt2.putConstraint("East", qty2, 0, "West", gal2);
		statusLyt2.putConstraint("West", price2, 0, "West", qty2);
		statusLyt2.putConstraint("East", price2, 0, "West", usd2);
		statusLyt2.putConstraint("West", type2, 0, "West", qty2);
		statusLyt2.putConstraint("East", type2, 0, "East", gal2);
		statusLyt2.putConstraint("VerticalCenter", qty2, 0, "VerticalCenter", status2);
		statusLyt2.putConstraint("South", type2, 0, "North", qty2);
		statusLyt2.putConstraint("North", price2, 0, "South", qty2);
		statusLyt2.putConstraint("VerticalCenter", gal2, 0, "VerticalCenter", status2);
		statusLyt2.putConstraint("East", gal2, -10, "East", status2);
		statusLyt2.putConstraint("North", usd2, 0, "South", qty2);
		statusLyt2.putConstraint("East", usd2, -10, "East", status2);
		layout.putConstraint("North", menu, 0, "North", panel);
		layout.putConstraint("West", menu, 0, "West", panel);
		layout.putConstraint("East", menu, -346, "East", panel);
		layout.putConstraint("South", menu, 0, "South", panel);
		layout.putConstraint("West", status, 5, "East", menu);
		layout.putConstraint("North", status, 0, "South", select);
		layout.putConstraint("East", status, 0, "East", panel);
		layout.putConstraint("South", status, 0, "South", panel);
		layout.putConstraint("North", select, 0, "North", panel);
		layout.putConstraint("West", select, 5, "East", menu);
		layout.putConstraint("East", select, 0, "East", panel);

		setVisible(true);

		// to make sure the selected pump is always properly highlighted
		switch(pumpSelect) {
			case 0:
				status0.setBackground(new Color(0, 0, 128));
				break;
			case 1:
				status1.setBackground(new Color(0, 0, 128));
				break;
			case 2:
				status2.setBackground(new Color(0, 0, 128));
		}
	}

	private class setPrice implements ActionListener {
		private TextField price0, price1, price2;
		private Panel menu;
		public setPrice(TextField price0, TextField price1, TextField price2, Panel menu) {
			this.price0 = price0;
			this.price1 = price1;
			this.price2 = price2;
			this.menu = menu;
		}
		public void actionPerformed(ActionEvent e) { // rearranges part of the window to show setPrice menu
			SpringLayout menuLyt = new SpringLayout();

			menu.removeAll();
			menu.setLayout(menuLyt);

			Label priceL = new Label("Price:", Label.CENTER);
			TextField priceF = new TextField();
			Button btnOk = new Button("OK");
			Button btnCancel = new Button("Cancel");

			btnOk.addActionListener(new setPriceOK(price0, price1, price2, menu, priceF, priceL));
			btnCancel.addActionListener(new setPriceOK(price0, price1, price2, menu, priceF, priceL));

			menu.add(priceL);
			menu.add(priceF);
			menu.add(btnOk);
			menu.add(btnCancel);

			menuLyt.putConstraint("VerticalCenter", priceF, 0, "VerticalCenter", menu);
			menuLyt.putConstraint("HorizontalCenter", priceF, 0, "HorizontalCenter", menu);
			menuLyt.putConstraint("West", priceF, 10, "West", menu);
			menuLyt.putConstraint("East", priceF, -10, "East", menu);
			menuLyt.putConstraint("West", priceL, 0, "West", priceF);
			menuLyt.putConstraint("East", priceL, 0, "East", priceF);
			menuLyt.putConstraint("South", priceL, 0, "North", priceF);
			menuLyt.putConstraint("North", btnOk, 0, "South", priceF);
			menuLyt.putConstraint("North", btnCancel, 0, "South", priceF);
			menuLyt.putConstraint("West", btnOk, -42, "HorizontalCenter", priceF);
			menuLyt.putConstraint("East", btnCancel, 42, "HorizontalCenter", priceF);

			validate();
		}
	}

	private class setPriceOK implements ActionListener { // used when pressing OK button or cancel button of setPrice menu
		private TextField price0, price1, price2;
		private TextField priceF;
		private Panel menu;
		private Label priceL;
		public setPriceOK(TextField price0, TextField price1, TextField price2, Panel menu, TextField priceF, Label priceL) {
			this.price0 = price0;
			this.price1 = price1;
			this.price2 = price2;
			this.menu = menu;
			this.priceF = priceF;
			this.priceL = priceL;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "OK") { // check if OK button pressed and not cancel button
				try {
					pump[pumpSelect].setGalPrice(Double.parseDouble(priceF.getText()));
				} catch(NumberFormatException n) { // if not valid input
					priceL.setText("Invalid price");
					priceL.setForeground(Color.red);
					return;
				} catch(PriceException p) { // if resulting price >100 or <1
					priceL.setText("Exceeds limit");
					priceL.setForeground(Color.red);
					return;
				}
			}
			load(); // return to main menu regardless of button pressed
		}
	}

	private class addGal implements ActionListener {
		private TextField qty0, qty1, qty2;
		private Panel menu;
		public addGal(TextField qty0, TextField qty1, TextField qty2, Panel menu) {
			this.qty0 = qty0;
			this.qty1 = qty1;
			this.qty2 = qty2;
			this.menu = menu;
		}
		public void actionPerformed(ActionEvent e) { // rearranges part of the window to show addGal menu
			SpringLayout menuLyt = new SpringLayout();

			menu.removeAll();
			menu.setLayout(menuLyt);

			Label qtyL = new Label("Gallons:", Label.CENTER);
			TextField qtyF = new TextField();
			Button btnOk = new Button("OK");
			Button btnCancel = new Button("Cancel");

			btnOk.addActionListener(new addGalOK(qty0, qty1, qty2, menu, qtyF, qtyL));
			btnCancel.addActionListener(new addGalOK(qty0, qty1, qty2, menu, qtyF, qtyL));

			menu.add(qtyL);
			menu.add(qtyF);
			menu.add(btnOk);
			menu.add(btnCancel);

			menuLyt.putConstraint("VerticalCenter", qtyF, 0, "VerticalCenter", menu);
			menuLyt.putConstraint("HorizontalCenter", qtyF, 0, "HorizontalCenter", menu);
			menuLyt.putConstraint("West", qtyF, 10, "West", menu);
			menuLyt.putConstraint("East", qtyF, -10, "East", menu);
			menuLyt.putConstraint("West", qtyL, 0, "West", qtyF);
			menuLyt.putConstraint("East", qtyL, 0, "East", qtyF);
			menuLyt.putConstraint("South", qtyL, 0, "North", qtyF);
			menuLyt.putConstraint("North", btnOk, 0, "South", qtyF);
			menuLyt.putConstraint("North", btnCancel, 0, "South", qtyF);
			menuLyt.putConstraint("West", btnOk, -42, "HorizontalCenter", qtyF);
			menuLyt.putConstraint("East", btnCancel, 42, "HorizontalCenter", qtyF);

			validate();
		}
	}

	private class addGalOK implements ActionListener { // used when pressing OK button or cancel button from addGal menu
		private TextField qty0, qty1, qty2;
		private TextField qtyF;
		private Panel menu;
		private Label qtyL;
		public addGalOK(TextField qty0, TextField qty1, TextField qty2, Panel menu, TextField qtyF, Label qtyL) {
			this.qty0 = qty0;
			this.qty1 = qty1;
			this.qty2 = qty2;
			this.menu = menu;
			this.qtyF = qtyF;
			this.qtyL = qtyL;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "OK") { // check if OK button pressed and not cancel button
				try {
					pump[pumpSelect].modGalQty(Double.parseDouble(qtyF.getText()));
				} catch(NumberFormatException n) { // if not valid input
					qtyL.setText("Invalid number");
					qtyL.setForeground(Color.red);
					return;
				} catch(QtyException q) { // if resulting quantity is >50000
					qtyL.setText("Exceeds capacity");
					qtyL.setForeground(Color.red);
					return;
				}
			}
			load(); // return to main menu regardless of button pressed
		}
	}

	private class setQty implements ActionListener {
		private TextField qty0, qty1, qty2;
		public setQty(TextField qty0, TextField qty1, TextField qty2) {
			this.qty0 = qty0;
			this.qty1 = qty1;
			this.qty2 = qty2;
		}
		public void actionPerformed(ActionEvent e) {
			pump[pumpSelect].setGalQty();
			// all displays updated--this too would be much more efficient if I had made arrays of labels...
			qty0.setText(String.format("%.2f",pump[0].getGalQty()));
			qty1.setText(String.format("%.2f",pump[1].getGalQty()));
			qty2.setText(String.format("%.2f",pump[2].getGalQty()));
		}
	}

	private class Select implements ActionListener { // used when pressing pump selection buttons
		private Panel status0, status1, status2;
		public Select(Panel status0, Panel status1, Panel status2) {
			this.status0 = status0;
			this.status1 = status1;
			this.status2 = status2;
		}
		public void actionPerformed(ActionEvent e) { // highlights a border around pump status
			if(e.getActionCommand() == "Select Pump 0") {
				pumpSelect = 0;
				status0.setBackground(new Color(0, 0, 128));
				status1.setBackground(Color.white);
				status2.setBackground(Color.white);
			} else
				if(e.getActionCommand() == "Select Pump 1") {
					pumpSelect = 1;
					status0.setBackground(Color.white);
					status1.setBackground(new Color(0, 0, 128));
					status2.setBackground(Color.white);
				} else
					if(e.getActionCommand() == "Select Pump 2") {
						pumpSelect = 2;
						status0.setBackground(Color.white);
						status1.setBackground(Color.white);
						status2.setBackground(new Color(0, 0, 128));
					}
		}
	}

	private class sell implements ActionListener {
		private TextField qty0, qty1, qty2;
		private Panel menu;
		public sell(TextField qty0, TextField qty1, TextField qty2, Panel menu) {
			this.qty0 = qty0;
			this.qty1 = qty1;
			this.qty2 = qty2;
			this.menu = menu;
		}
		public void actionPerformed(ActionEvent e) { // rearranges part of the window to show sell menu
			SpringLayout menuLyt = new SpringLayout();

			menu.removeAll();
			menu.setLayout(menuLyt);

			Label qtyL = new Label("Gallons:", Label.CENTER);
			TextField qtyF = new TextField();
			Button btnOk = new Button("OK");
			Button btnCancel = new Button("Cancel");

			btnOk.addActionListener(new sellOK(qty0, qty1, qty2, menu, qtyF, qtyL));
			btnCancel.addActionListener(new sellOK(qty0, qty1, qty2, menu, qtyF, qtyL));

			menu.add(qtyL);
			menu.add(qtyF);
			menu.add(btnOk);
			menu.add(btnCancel);

			menuLyt.putConstraint("VerticalCenter", qtyF, 0, "VerticalCenter", menu);
			menuLyt.putConstraint("HorizontalCenter", qtyF, 0, "HorizontalCenter", menu);
			menuLyt.putConstraint("West", qtyF, 10, "West", menu);
			menuLyt.putConstraint("East", qtyF, -10, "East", menu);
			menuLyt.putConstraint("West", qtyL, 0, "West", qtyF);
			menuLyt.putConstraint("East", qtyL, 0, "East", qtyF);
			menuLyt.putConstraint("South", qtyL, 0, "North", qtyF);
			menuLyt.putConstraint("North", btnOk, 0, "South", qtyF);
			menuLyt.putConstraint("North", btnCancel, 0, "South", qtyF);
			menuLyt.putConstraint("West", btnOk, -42, "HorizontalCenter", qtyF);
			menuLyt.putConstraint("East", btnCancel, 42, "HorizontalCenter", qtyF);

			validate();
		}
	}

	private class sellOK implements ActionListener { // used when pressing OK button or cancel button from sell menu
		private TextField qty0, qty1, qty2;
		private TextField qtyF;
		private Panel menu;
		private Label qtyL;
		public sellOK(TextField qty0, TextField qty1, TextField qty2, Panel menu, TextField qtyF, Label qtyL) {
			this.qty0 = qty0;
			this.qty1 = qty1;
			this.qty2 = qty2;
			this.menu = menu;
			this.qtyF = qtyF;
			this.qtyL = qtyL;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "OK") { // check if OK button pressed and not cancel button
				try {
					saleSumm(qtyF, menu); // displays a sale summary
				} catch(NumberFormatException n) { // if not valid input
					qtyL.setText("Invalid number");
					qtyL.setForeground(Color.red);
					return;
				}
			} else
				load(); // return to main menu if cancel button pressed
		}

		private void saleSumm(TextField qtyF, Panel menu) {
			double dspQty;
			Label rqstd = new Label(String.format("Gal requested: %.2f", Double.parseDouble(qtyF.getText())));
			Label dspnsd = new Label();
			if(Double.parseDouble(qtyF.getText()) > pump[pumpSelect].getGalQty())
				dspQty = pump[pumpSelect].getGalQty();
			else
				dspQty = Double.parseDouble(qtyF.getText());
			dspnsd.setText(String.format("Gal dispensed: %.2f",  dspQty));
			Label sale = new Label(String.format("Sale: %.2f USD", pump[pumpSelect].sell(Double.parseDouble(qtyF.getText()))));
			Label rmndr = new Label(String.format("Gal remaining: %.2f", pump[pumpSelect].getGalQty()));


			Button btnOk = new Button("OK");
			btnOk.addActionListener(new SummOK());

			SpringLayout menuLyt = new SpringLayout();

			menu.removeAll();
			menu.setLayout(menuLyt);
			menu.add(rqstd);
			menu.add(dspnsd);
			menu.add(rmndr);
			menu.add(sale);
			menu.add(btnOk);

			menuLyt.putConstraint("North", rqstd, 0, "North", menu);
			menuLyt.putConstraint("North", dspnsd, 0, "South", rqstd);
			menuLyt.putConstraint("North", rmndr, 0, "South", dspnsd);
			menuLyt.putConstraint("North", sale, 0, "South", rmndr);
			menuLyt.putConstraint("East", rqstd, 0, "East", menu);
			menuLyt.putConstraint("East", dspnsd, 0, "East", menu);
			menuLyt.putConstraint("East", rmndr, 0, "East", menu);
			menuLyt.putConstraint("East", sale, 0, "East", menu);
			menuLyt.putConstraint("South", btnOk, 0, "South", menu);
			menuLyt.putConstraint("HorizontalCenter", btnOk, 0, "HorizontalCenter", menu);

			validate();
		}
	}

	private class SummOK implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			load(); // return to main menu after user views sale summary and presses OK button
		}
	}

	public void windowClosing(WindowEvent e) {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	public void windowOpened(WindowEvent e) { }
	public void windowClosed(WindowEvent e) { }
	public void windowIconified(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowActivated(WindowEvent e) { }
	public void windowDeactivated(WindowEvent e) { }
}