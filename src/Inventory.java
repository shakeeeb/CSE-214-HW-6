import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**CSE 214 Homework 6
 * ID: 109239204
 * @author Shakeeb Saleh
 */

public class Inventory implements Serializable {
	/**
	 * a Hashtable whose keys are item names, 
	 *  and whose values are the quantity of the corresponding item
	 */
	private Hashtable<String, Integer> quantities = new Hashtable<String, Integer>(); 
	/**
	 * a Hashtable whose keys are item names, 
	 * and whose values are the location of the corresponding item
	 */
	private Hashtable<String, String> itemToLocation = new Hashtable<String, String>(); 
	/**
	 * a Hashtable whose keys are locations,
	 * and whose values are a list of all the items at that location
	 */
	private Hashtable<String, ArrayList<String>> locationToItem = new Hashtable<String, ArrayList<String>>(); 
	/**
	 * empty default constructor
	 */
	public Inventory(){
		
	}
	/**
	 * Overloaded Constructor
	 * @param itemName - name of item
	 * @param location - locaiton in store
	 * @param quantity - quantity of item
	 */
	public Inventory(String itemName, String location, int quantity){
		quantities.put(itemName, quantity);
		itemToLocation.put(itemName, location);
		ArrayList<String> bleh = new ArrayList<String>();
		bleh.add(itemName);
		locationToItem.put(location, bleh);
	}
	/** NewItem
	 * Insert a new item into the store.
	 * All three Hashtables will need to be updated. 
	 * Throw an exception if the item is already in the store.
	 * @param item
	 * @param location
	 * @param quantity
	 */
	public void newItem(String item, String location, int quantity){
		if (itemToLocation.containsKey(item)){
			throw new IllegalArgumentException();
		}
		quantities.put(item, quantity);
		itemToLocation.put(item, location);
		if(locationToItem.containsKey(location)){
			ArrayList<String> bleh = locationToItem.remove(location);
			bleh.add(item); 
			locationToItem.put(location, bleh);
		} else {
			ArrayList<String> bleh = new ArrayList<String>();
			bleh.add(item);
			locationToItem.put(location, bleh);
		}
	}
	/**moveItem 
	 * change the location of the given item to the new value. 
	 * Only itemToLocation and locationToItem will need to be updated. 
	 * Throw an exception if the item could not be found.
	 * @param item
	 * @param newLocation
	 */
	public void moveItem(String item, String newLocation){
		if (!(itemToLocation.containsKey(item))){
			throw new IllegalArgumentException();
		}
		
		String hold = itemToLocation.remove(item); // hold is the old location
		itemToLocation.put(item, newLocation);
		
		ArrayList<String> bleh = locationToItem.remove(hold);
		bleh.remove(item); 
		if(locationToItem.containsKey(newLocation)){// the location is a differnet place within the store
			locationToItem.put(hold, bleh);
			ArrayList<String> bloh = locationToItem.remove(newLocation);
			bloh.add(item);
			locationToItem.put(newLocation, bloh);
		} else { // if the location is an enitrely new place
			locationToItem.put(hold, bleh);
			ArrayList<String> bluh = new ArrayList<String>();
			bluh.add(item);
			locationToItem.put(newLocation, bluh);
		}
	}
	/**
	 * removeItem
	 *  remove an item from the store. 
	 *  All three Hashtables will need to be updated. 
	 *  Throw an exception if the item could not be found.
	 * @param item
	 */
	public void removeItem(String item){
		if (!(itemToLocation.containsKey(item))){
			throw new IllegalArgumentException();
		}
		
		String hold = itemToLocation.remove(item);
		ArrayList<String> bleh = locationToItem.remove(hold);
		bleh.remove(item);
		locationToItem.put(hold, bleh);
		quantities.remove(item);
	}
	/**
	 * updateQuantity
	 * change the quantity of the given item to the new value. 
	 * Only quantities will need to be updated. 
	 * Throw an exception if the item could not be found.
	 * @param item
	 * @param newQuantity
	 */
	 public void updateQuantity(String item, int newQuantity){
		 if (!(quantities.containsKey(item))){
			 throw new IllegalArgumentException();
		 }
		 
		 quantities.remove(item);
		 quantities.put(item, newQuantity);
	 }
	 /**hasItem
	  * return true if the given item is in the store, false otherwise.
	  * @param item
	  * @return
	  */
	 public boolean hasItem(String item){
		 return itemToLocation.containsKey(item);
	 }
	 /**locationOf
	  * return the location of the given item. 
	  * Throw an exception if the item could not be found.
	  * @param item
	  * @return
	  */
	 public String locationOf(String item){
		 if (!(itemToLocation.containsKey(item))){
			 throw new IllegalArgumentException();
		 } 
		 return itemToLocation.get(item);
	 }
	 /**
	  * quantityOf
	  * return the quantity of the given item. 
	  * Throw an exception if the item could not be found.
	  * @param item
	  * @return
	  */
	 public int quantityOf(String item){
		 if (!(quantities.containsKey(item))){
			 throw new IllegalArgumentException();
		 }
		 return quantities.get(item);
	 }
	 /**itemsAt
	  *  return the list of items at the given location. 
	  *  Throw an exception if the location does not exist.
	  * @param location
	  * @return
	  */
	 public ArrayList<String> itemsAt(String location){
		 if (!(locationToItem.containsKey(location))){
			 throw new IllegalArgumentException();
		 }
		 return locationToItem.get(location);
	 }
	 /**MaxStockAT
	  * return the name of the item with the highest quantity at the given location. 
	  * Throw an exception if the location does not exist or has no items.
	  * @param location
	  * @return
	  */
	 public String maxStockAt(String location){// iterate through the list of items at the location
		 // finding the highest quantity relative to item itself
		 ArrayList<String> bleh = locationToItem.get(location);
		 int maxQ = 0; String itemQ = "";
		 for(int i = 0; i < bleh.size(); i++){
			 if (!(quantities.containsKey(bleh.get(i)))){
				 throw new IllegalArgumentException();
			 }
			 if(quantities.get(bleh.get(i)) > maxQ){
				 maxQ = quantities.get(bleh.get(i));
				 itemQ = bleh.get(i);
			 }
		 }
		 return itemQ;
	 }
	 /**printAllItems
	  * print each location in the store 
	  * followed by a list of each item name 
	  * and quantity in that location
	  */
	 public void printAllItems(){
		 /*
		  * location
		  *   item1 quantity
		  *   item2 quantity
		  *   etc..
		  * nextLocation
		  *   item 6 quantity
		  *   ...  
		  */
		 String result = "";
		 String line = "";
		 String currentKey = "";
		 String currentItem = "";
		 ArrayList<String> holder = new ArrayList<String>();
		 
		 for(Enumeration<String> e = locationToItem.keys(); e.hasMoreElements();){
			 currentKey = e.nextElement();
			 result += currentKey + "\n";
			 holder = locationToItem.get(currentKey);
			 for(int i=0; i<holder.size(); i++){
				 currentItem = holder.get(i);
				 line = " " + currentItem + " " + quantities.get(currentItem) + "\n";
				 result += line;
			 }
		 }
		 System.out.print(result);
	 }

}
