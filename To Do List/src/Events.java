import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Events implements Serializable{
	
	private static final long serialVersionUID = 1L;
	ArrayList<Event> events= new ArrayList<Event>();
	
	public ArrayList<Event> getEvents() {
		return events;
	}

	public void add(Event event) {
		events.add(event);
	}
	
	public void remove(Event event) {
		int i=0;
		for (Event eventCurr: events) {
			if (eventCurr.equals(event)){
				i=events.indexOf(eventCurr);
			}
		}
		events.remove(i);
	}
	
	public Event parseToEvent(String buttonText) {
		Scanner scan= new Scanner(buttonText);
		scan.useDelimiter(",");
		
		String urgency=scan.next();
		String importanceStr;
		Importance importance=null;
		
		if(urgency.equals("!!!")) {
			importanceStr= "URGENT";
			importance=Importance.valueOf(importanceStr);
		}
		else if (urgency.equals("!!")) {
			importanceStr= "IMPORTANT";
			importance=Importance.valueOf(importanceStr);
		}
		else if (urgency.equals("!")) {
			importanceStr= "DO_WHENEVER";
			importance=Importance.valueOf(importanceStr);
		}
		
		String eventName=scan.next();
		
		String wholeDateString= scan.next();
		Scanner scanDate= new Scanner(wholeDateString);
		scanDate.useDelimiter(":");
		scanDate.next();
		String date=scanDate.next();
		scan.close();
		scanDate.close();
		
		Event event=new Event(eventName, date, importance);
		return event;
	}
	
}
