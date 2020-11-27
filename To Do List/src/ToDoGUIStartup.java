import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.eclipse.swt.widgets.Button;

public class ToDoGUIStartup {
	private static final String TODOGUISTATEFILE = "ToDoGUI.txt";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		ToDoGUI GUI;
		Events events;
		
		try {
			if(serializedFileExists(TODOGUISTATEFILE)) {
				events= loadToDoGUI();
			}
			else {
				events = new Events();
			}
			GUI= new ToDoGUI(events);
			GUI.open();
			
			in.close();
			writeToDoGUI(events);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// needed to serialize the list of events.
	private static Boolean serializedFileExists(String filePath) {
		File tmpDir = new File(filePath);
		return tmpDir.exists();
	}
	
	private static Events loadToDoGUI() throws IOException, ClassNotFoundException {
		Events savedEvents;
		FileInputStream fin = new FileInputStream(TODOGUISTATEFILE);
		ObjectInputStream oin = new ObjectInputStream(fin);
		savedEvents = (Events)oin.readObject();
		oin.close();
		return savedEvents;
	}

	private static void writeToDoGUI(Events savedEvents) throws Exception, IOException {
		ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(TODOGUISTATEFILE)); 
		out.writeObject(savedEvents); 
		out.close();
	}
}
