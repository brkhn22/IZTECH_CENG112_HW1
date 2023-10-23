package garbage.collector;

import java.util.Arrays;

public class TrashCan<T> implements IBag<T> {
	
	private static final int DEFAULT_TRASH_CAN_SIZE = 450;
	private T[] bag;
	private int nOfItems;
	private int capacity;
	
	private GarbageRecyclingApp gApp;
	public TrashCan(GarbageRecyclingApp gApp) {
		this(gApp, DEFAULT_TRASH_CAN_SIZE);
	}
	@SuppressWarnings("unchecked")
	public TrashCan(GarbageRecyclingApp gApp,int size) {
		bag = (T[]) new Object[size];
		capacity = size;
		nOfItems = 0;
		this.gApp = gApp;
	}

	public boolean separate(T item) {
		boolean result = false;
		String type = ((Garbage)item).type;
			switch(type) {
				case Garbage.TRASH_TYPE_PLASTIC:
					result = transferTo(gApp.plasticRecycleBin, item);
					if(!result) this.add(item);
					break;
				case Garbage.TRASH_TYPE_GLASS:
					result = transferTo(gApp.glassRecycleBin, item);
					if(!result) this.add(item);
					break;
				case Garbage.TRASH_TYPE_PAPER:
					result = transferTo(gApp.paperRecycleBin, item);
					if(!result) this.add(item);
					break;
				case Garbage.TRASH_TYPE_FABRIC:
					result = transferTo(gApp.fabricRecycleBin, item);
					if(!result) this.add(item);
					break;
				case Garbage.TRASH_TYPE_ORGANIC:
					result = transferTo(gApp.organicRecycleBin, item);
					if(!result) this.add(item);
					break;
				case Garbage.TRASH_TYPE_METAL:
					result = transferTo(gApp.metalRecycleBin, item);
					if(!result) this.add(item);
					break;
			}
		return result;
	}

	public boolean add(T object) {
		boolean result = true;
		if(object == null || isFull()) result = false;
		else {
			bag[nOfItems] = object;
			nOfItems++;
		}
		
		return result;
	}
	public boolean isEmpty() {
		return nOfItems == 0;
	}
	public boolean isFull() {
		return nOfItems >= capacity;
	}
	public T removeByIndex(int index) throws IndexOutOfBoundsException{
		T removedItem = null;
		if(index < nOfItems && index >= 0)
		{
			removedItem = bag[index];
			// Be careful about the order of items
			for (int i = index; i < (nOfItems-1);i++) 
				bag[i] = bag[i+1];
			
			nOfItems--;
		}else throw new IndexOutOfBoundsException("Index is out of bounds of this bag.");
		
		return removedItem;
	}
	public T remove() throws EmptyBagException {
		if(isEmpty()) throw new EmptyBagException();
		
		T removedItem = bag[nOfItems-1];
		bag[nOfItems-1] = null;
		nOfItems--;
		
		return removedItem;
	}
	
	public T remove(T object) {
		T removedItem = null;
		int index = getIndexOf(object);
		if(index > -1) removedItem = removeByIndex(index);
		
		return removedItem;
		
	}
	
	public int getIndexOf(T object) {
		int counter = 0;
		int found = -1;
		while(counter < nOfItems && found == -1) {
			if(object.equals(bag[counter]))
				found = counter;
			counter++;
		}
		return found;
	}
	
	public int getItemCount() {
		return nOfItems;
	}
	
	public boolean contains(T object) {
		return getIndexOf(object) > -1;
	}
	
	public void displayItems() {
		if (isEmpty()) return;
		System.out.println("The Trash Can: "+" this can has "+this.getItemCount()+" garbage. Contents:");
		T[] tempBag = Arrays.copyOf(bag, nOfItems);
		int indexer = tempBag.length-1;
		while (indexer >= 0) {
			T tempItem = tempBag[indexer];
			int recurrency = 1;
			if(indexer > 0) {
				for(int i = indexer-1; i >=0; i--) {
					if(tempItem.equals(tempBag[i]))
						recurrency++;
					else 
						break;
					
				}
			}
			System.out.println(recurrency+" "+tempItem.toString());
			indexer -= recurrency;
		}
	}
	
	public void dump() {
		for(int i = 0; i < nOfItems; i++) bag[i] = null;
	}
	
	 public <I> boolean transferTo(IBag<I> targetBag, T item) {
		 boolean result = false;
		 @SuppressWarnings("unchecked")
		 I newItem = (I)item;
		 if(targetBag.add(newItem))
			 result = true;
		 return result;
	 }
}
	
