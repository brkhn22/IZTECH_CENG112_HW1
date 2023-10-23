package garbage.bin;

import java.util.Arrays;
import java.util.Random;

import garbage.collector.IBag;


public class PaperRecycleBin<T> implements IBag<T> {
	

	private T[] bag;
	private int numOfEntries;
    private int[] possibleSizes = new int[]{5,10,15};
    private Random rand = new Random();
	private int size;
	
	
	@SuppressWarnings("unchecked")
	public PaperRecycleBin() {
        size = possibleSizes[rand.nextInt(possibleSizes.length)];
        numOfEntries = 0;
        bag = (T[]) new Object[size];
	}
	
	public int getSize() {
		return size;
	}
	
	@Override
	public boolean add(T newObject) {
		boolean result = true;
		if(isBinFull()) {
			return false;
		}
		else {
			bag[numOfEntries] = newObject;
			numOfEntries ++; 
		}
		return result;
	}
	
	public boolean isBinEmpty() {
		return numOfEntries == 0;
	}
	
	public boolean isBinFull() {
		return numOfEntries == size;
	}
	
	public boolean contains(T anObject) {
		boolean found = false;
		int index = 0;
		while(!found && (index < numOfEntries)) {
			if (anObject.equals(bag[index])) {
				found = true;
			}
			index ++;
		}
		return found;
	}
	
	public void dump() {
		while(!isBinEmpty()) {
			remove();
		}
	}
	
	public T remove() {
		T result = null;
		if (numOfEntries > 0) {
			result = bag[numOfEntries - 1];
			bag[numOfEntries - 1] = null;
			numOfEntries --; 
		}
		return result;
	}
	
	public T remove(T anObject) {
		int index = getIndexOf(anObject);
		T result = removeByIndex(index);
		return result;
		
	}
	
	public T removeByIndex(int givenIndex) {
		T result = null;
		if (!isBinEmpty() && (givenIndex >= 0)) {
			result = bag[givenIndex];
			bag[givenIndex] = bag[numOfEntries - 1];
			bag[numOfEntries - 1] = null;
			numOfEntries --;
		}
		return result;
	}
	
	public int getIndexOf(T anObject) {
		int location = -1;
		boolean found = false;
		int index = 0;
		while(!found && (index < numOfEntries)) {
			if (anObject.equals(bag[index])) {
				found = true;
				location = index;
			}
			index ++;
		}
		return location;
	}

	public boolean isEmpty() {
		return numOfEntries == 0;
	}
	public boolean isFull() {
		return numOfEntries >= size;
	}

	public int getItemCount() {
		return numOfEntries;
	}
	public void displayItems() {
		if (isEmpty()) return;
		System.out.println("The Paper Recycle Bin: "+" this can has "+this.getItemCount()+" garbage. Contents:");
		T[] tempBag = Arrays.copyOf(bag, numOfEntries);
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

	 public <I> boolean transferTo(IBag<I> targetBag, T item) {
		 boolean result = false;
		 @SuppressWarnings("unchecked")
		 I newItem = (I)item;
		 if(targetBag.add(newItem))
			 result = true;
		 return result;
	 }
	
}
	
	
