import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
/**
 * InventoryManager
 * this all just gui stuff, mostly
 * @author Shakeeb saleh
 *
 */
public class InventoryManager extends JFrame{
	/**A) add item – prompt for item name, location, and quantity, and add to the store.
R) remove item – prompt for item name and remove from the store.
M) move item – prompt for item name and new location, and update the store
I) get item – prompt for item name and display quantity and location
U) update quantity – prompt for item name and new quantity, and update the store.
L) list all items at a given location – prompt for location and print all items at that location
O) get item with max quantity at a location – prompt for location and print the item with the highest quantity at that location
P) print all items in the store grouped by location
Q) quit (and save the data to “store.obj”)
	 * 
	 */

	JButton addItem = new JButton("Add an item to the Inventory");
	JButton removeItem = new JButton("remove a selected item for the inventory");
	JButton moveItem = new JButton("move an item form one location to a different location");
	JButton getItem = new JButton("retrieve an items quantity and location");
	JButton updateQuantity = new JButton("Update the quantity of an item in stock");
	JButton listAllGivenLoc = new JButton("List all item given the location of an item");
	JButton getItemMaxStock = new JButton("find the item most in stock at a given location");
	JButton printAll = new JButton("Print all items in the store by location");
	JButton saveAndQuit = new JButton("Save and Quit");
	
	Inventory stock = new Inventory();
	
	public InventoryManager(){
		this.setSize(400,600);
		this.setLayout(new GridLayout(9,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(addItem);
		this.add(removeItem);
		this.add(moveItem);
		this.add(getItem);
		this.add(updateQuantity);
		this.add(listAllGivenLoc);
		this.add(getItemMaxStock);
		this.add(printAll);
		this.add(saveAndQuit);
		
		addItem.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(1);}});
		removeItem.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(2);}});
		moveItem.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(3);}});
		getItem.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(4);}});
		updateQuantity.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(5);}});
		listAllGivenLoc.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(6);}});
		getItemMaxStock.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(7);}});
		printAll.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(8);}});
		saveAndQuit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(9);}});
		
		setVisible(true);
	}
	public InventoryManager(Inventory store){
		this.setSize(400,600);
		this.setLayout(new GridLayout(9,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(addItem);
		this.add(removeItem);
		this.add(moveItem);
		this.add(getItem);
		this.add(updateQuantity);
		this.add(listAllGivenLoc);
		this.add(getItemMaxStock);
		this.add(printAll);
		this.add(saveAndQuit);
		
		addItem.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(1);}});
		removeItem.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(2);}});
		moveItem.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(3);}});
		getItem.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(4);}});
		updateQuantity.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(5);}});
		listAllGivenLoc.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(6);}});
		getItemMaxStock.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(7);}});
		printAll.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(8);}});
		saveAndQuit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){activate(9);}});
		
		stock = store;
		
		setVisible(true);
	}
	public void activate(int option){
		switch(option){
		case 1:{ // add an item to the inventory
			String item, location;
			int quantity;
			item = JOptionPane.showInputDialog("What kind of item should we Stock?");
			location = JOptionPane.showInputDialog("Where in the store should we Stock it?");
			quantity = Integer.parseInt(JOptionPane.showInputDialog("How many should we keep stocked (in numbers)"));
			stock.newItem(item,location, quantity);
			JOptionPane.showMessageDialog(null, "We've ordered " + quantity + " " + item +"s to " + location + ".");
			}break;
		case 2:{ //remove an item from inventory
			String item;
			item = JOptionPane.showInputDialog("What kind of item should we clear?");
			stock.removeItem(item);
			JOptionPane.showMessageDialog(null, "" + item + " has been put on Clearance.");
			}break;
		case 3:{ //move Item
			String item, location;
			item = JOptionPane.showInputDialog("Which item should we move?");
			location = JOptionPane.showInputDialog("Where should we move it?");
			stock.moveItem(item, location);
			}break;
		case 4:{ //get item
			String item, location;
			int quantity;
			item = JOptionPane.showInputDialog("Which item's data should we retrieve?");
			location = stock.locationOf(item);
			quantity = stock.quantityOf(item);
			JOptionPane.showMessageDialog(null, "We have "+ quantity +" " + item + "s at "+ location + ".");
			}break;
		case 5:{ // update quantity
			String item;
			int quantity;
			item = JOptionPane.showInputDialog("Which item's quantity should be adjusted?");
			quantity = Integer.parseInt(JOptionPane.showInputDialog("What should the new amount of"+ item + "s be?"));
			stock.updateQuantity(item, quantity);
			JOptionPane.showMessageDialog(null, "We now have "+ quantity +" " + item + "s.");
			}break;
		case 6:{ // list all given location
			String location, result;
			ArrayList<String> holder;
			location = JOptionPane.showInputDialog("What is the location you wish to review?");
			holder = stock.itemsAt(location);
			result = location + " has:\n";
			result += holder.toString();
			JOptionPane.showMessageDialog(null, result);
			}break;
		case 7:{ // get item max stock in a single location
			String location, result;
			location = JOptionPane.showInputDialog("Which location do you wish to review?");
			result = stock.maxStockAt(location);
			result += " is the most dominant thing in " + location;
			JOptionPane.showMessageDialog(null, result);
			}break;
		case 8:{ // print errythang
			JOptionPane.showMessageDialog(null, "look at the console");
			stock.printAllItems();
			}break;
		case 9:try { // save + quit
			FileOutputStream f = new FileOutputStream("stock.obj");
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(stock);
			System.exit(0);
			} catch (FileNotFoundException f){System.out.print("file not found"); }
			catch (IOException i){System.out.print("IO exception encountered");}
			break;
		}// end of switch
	}
	
	public static void main (String[] args){
		try{
		FileInputStream in = new FileInputStream("stock.obj");
		ObjectInputStream s = new ObjectInputStream(in);
		Inventory priorStock = (Inventory) s.readObject();
		InventoryManager store = new InventoryManager(priorStock);
		} catch (IOException i){System.out.println("old data not found.");InventoryManager store = new InventoryManager(); }
		catch (ClassNotFoundException f){System.out.println("old data not found.");InventoryManager store = new InventoryManager();}
		
	}
	
	
}
