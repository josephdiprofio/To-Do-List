
import java.util.ArrayList;

import org.eclipse.swt.widgets.Button;

public class Buttons{
	
	
	private ArrayList<Button> listButtons=new ArrayList<Button>();
	int count=0;
	
	public void add(Button button) {
		listButtons.add(button);
	}
	
	public void remove(Button button) {
		int i=0;
		for(Button currButton: listButtons) {
			if (currButton.equals(button)) {
				i=listButtons.indexOf(currButton);
			}
		}
		listButtons.remove(i);
	}
	
	public void incrementCount() {
		count++;
	}
	
	public void decrementCount() {
		count--;
	}

	public ArrayList<Button> getListButtons() {
		return listButtons;
	}
	
}
