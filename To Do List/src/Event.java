import java.io.Serializable;

public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String name;
	String date;
	Importance importance;
	String urgency="";
	
	public Event(String name, String date, Importance importance) {
		this.name=name;
		this.date=date;
		this.importance=importance;
	}
	
	public void setUrgency() {
		if(importance== Importance.URGENT) {
			urgency= "!!!";
		}
		else if (importance== Importance.IMPORTANT) {
			urgency= "!!";
		}
		else if (importance== Importance.DO_WHENEVER) {
			urgency= "!";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Importance getImportance() {
		return importance;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
	}
	
	
	
	
}
