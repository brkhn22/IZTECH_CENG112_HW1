package garbage.collector;

import java.util.EmptyStackException;

public class EmptyBagException extends EmptyStackException {
	
	public EmptyBagException() {
		System.err.println("Bag is empty. Cannot remove an item.");
	}
}
