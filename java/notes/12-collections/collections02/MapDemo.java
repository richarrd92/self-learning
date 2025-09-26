
import java.util.*;

public class MapDemo {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// HashMap - unordered based on keys
		HashMap hm = new HashMap();
		hm.put("monitor", 5000);
		hm.put("keyboard", 500);
		hm.put("mouse", 300);
		hm.put("ups", 2000);
		hm.put("speakers", 1000);
		System.out.println(hm);// {keyboard=500, mouse=300, speakers=1000, ups=2000, monitor=5000}
		// LinkedHashMap - ordered based on keys
		LinkedHashMap lhm = new LinkedHashMap();
		lhm.put("monitor", 5000);
		lhm.put("keyboard", 500);
		lhm.put("mouse", 300);
		lhm.put("ups", 2000);
		lhm.put("speakers", 1000);
		System.out.println(lhm);// {monitor=5000, keyboard=500, mouse=300, ups=2000, speakers=1000}
		// TreeMap - sorted based on keys
		TreeMap tm = new TreeMap();
		tm.put("monitor", 5000);
		tm.put("keyboard", 500);
		tm.put("mouse", 300);
		tm.put("ups", 2000);
		tm.put("speakers", 1000);
		System.out.println(tm);//{keyboard=500, monitor=5000, mouse=300, speakers=1000, ups=2000}
		System.out.println(tm.descendingMap());//{ups=2000, speakers=1000, mouse=300, monitor=5000, keyboard=500}

	}

}
