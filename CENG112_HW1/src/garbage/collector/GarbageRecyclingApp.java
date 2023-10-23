package garbage.collector;

import garbage.bin.*;

public class GarbageRecyclingApp {
	
	public static FabricRecycleBin<Garbage> fabricRecycleBin;
	public static PlasticRecycleBin<Garbage> plasticRecycleBin;
	public static MetalRecycleBin<Garbage> metalRecycleBin;
	public static OrganicRecycleBin<Garbage> organicRecycleBin;
	public static PaperRecycleBin<Garbage> paperRecycleBin;
	public static GlassRecycleBin<Garbage> glassRecycleBin;
	
	public GarbageRecyclingApp() {
		fabricRecycleBin = new FabricRecycleBin<Garbage>();
		plasticRecycleBin = new PlasticRecycleBin<Garbage>();
		metalRecycleBin = new MetalRecycleBin<Garbage>();
		organicRecycleBin = new OrganicRecycleBin<Garbage>();
		paperRecycleBin = new PaperRecycleBin<Garbage>();
		glassRecycleBin = new GlassRecycleBin<Garbage>();
	}
	
	public static void main(String[] args) {
		FileIO io = new FileIO();
		TrashCan<Garbage> trashCan = (TrashCan<Garbage>)io.readTrashCan();
		trashCan.displayItems();
		// Simulate separation
		int size = trashCan.getItemCount();
		for(int i = size-1; i >= 0; i--) {
			Garbage item = trashCan.removeByIndex(i);
			trashCan.separate(item);
		}
		// Display the bins
		fabricRecycleBin.displayItems();
		plasticRecycleBin.displayItems();
		metalRecycleBin.displayItems();
		organicRecycleBin.displayItems();
		paperRecycleBin.displayItems();
		glassRecycleBin.displayItems();
		
		// Display the remain trash.
		trashCan.displayItems();
		
		// Update the garbage file.
		io.updateTrashCan(trashCan);
		
		// Dump all the recycle bins and the trash can
		trashCan.dump();
		fabricRecycleBin.dump();
		plasticRecycleBin.dump();
		metalRecycleBin.dump();
		organicRecycleBin.dump();
		paperRecycleBin.dump();
		glassRecycleBin.dump();
	}
}
