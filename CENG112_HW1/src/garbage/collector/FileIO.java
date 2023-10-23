package garbage.collector;

import java.io.*;

public class FileIO {
	
	public IBag<Garbage> readTrashCan() {
		IBag<Garbage> bag = new TrashCan<>(new GarbageRecyclingApp());
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./source/garbage.txt"));
				String row;
				String[] arrayOfRow;
				while((row = reader.readLine()) != null) {
					arrayOfRow = row.trim().split(",");
					String garbageName = arrayOfRow[0];
					String garbageType = arrayOfRow[1];
					int quantity = Integer.parseInt(arrayOfRow[2]);
					for(int i = 0; i < quantity; i++) {
						Garbage garbageElement = new Garbage(garbageName, garbageType);
						bag.add(garbageElement);
					}
				} 
			
		}
		catch (IOException frexp) {
			System.out.println(frexp);
		}
		
		return bag;
	}
	public boolean updateTrashCan(TrashCan<Garbage> trashCan) {
		boolean result = true;
	    try {
	        FileWriter file = new FileWriter("./source/updated-garbage.txt");
	        BufferedWriter writer = new BufferedWriter(file);
			while (!trashCan.isEmpty()) {
				Garbage tempGarbage = trashCan.remove();
				int recurrency = 1;
				while(!trashCan.isEmpty()) {
					Garbage upwardGarbage = trashCan.remove();
					if(tempGarbage.equals(upwardGarbage))
						recurrency++;
					else {
						trashCan.add(upwardGarbage);
						break;
					}
						
				}
				writer.write(tempGarbage.toString()+", "+tempGarbage.type+", "+recurrency);
				writer.newLine();
			}
	        writer.close();
	      } catch (Exception e) {
	        e.getStackTrace();
	        result = false;
	      }
	    return result;
	}
}
