package garbage.collector;

public interface IBag<T> {
	/* Adds new specific item to the Bag;
	 * Returns True if the item is added successfully.*/
	public boolean add(T newObject);
	
	/* Returns True if the Bag has no item.*/
	public boolean isEmpty();
	
	/* Returns True if the Bag is Full.*/
	public boolean isFull();
	
	/* Takes an integer as an argument for index and remove the corresponding item.
	 * Returns the removed item. */
	public T removeByIndex(int index);
	
	/* Removes the last item in the bag.
	 * Returns the removed item. */
	public T remove();
	
	/* Removes a specific item.
	 * Returns True if the item is successfully removed. */
	public T remove(T object);
	
	/* Returns the number of items currently in the Bag. */
	public int getItemCount();
	
	/* Returns the index of a certain item in the Bag. */
	public int getIndexOf(T object);
	
	/* Returns True of the specified item exists in the Bag */
	public boolean contains(T object);
	
	/* Displays the items currently existing in the bag */
	public void displayItems();
	
	/* Removes all the items from the bag; #clears the bag. */
	public void dump();

	
	public <I> boolean transferTo(IBag<I> targetBag, T item);
	
}
