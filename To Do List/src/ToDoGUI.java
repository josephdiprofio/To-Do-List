import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;


public class ToDoGUI{

	
	protected Shell shlSwtApplication;
	private Text titleText;
	private Text dateText;
	Buttons buttons=new Buttons();
	String titleCurrentText="";
	String dateCurrentText="";
	int currentY=85;
	String urgency="";
	Events events=new Events();
	
	public ToDoGUI(Events events) {
		this.events=events;
	}
	

	/**
	 * Launch the application.
	 * @param args
	 */
	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSwtApplication.open();
		shlSwtApplication.layout();
		while (!shlSwtApplication.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSwtApplication = new Shell();
		shlSwtApplication.setSize(475, 550);
		shlSwtApplication.setText("SWT Application");
		
		//if events already exist, reprint them.
		if(!events.getEvents().isEmpty()) {
			for (Event event: events.getEvents()) {
				Button btnStored = new Button(shlSwtApplication, SWT.CHECK);
				btnStored.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent e) {
						deleteAndAlign(btnStored);
					}
				});
				btnStored.setBounds(10, currentY, 433, 25);
				btnStored.setText(event.urgency+","+event.getName()+", Do By: "+event.getDate());
				buttons.add(btnStored);
				buttons.incrementCount();
				currentY+=30;
			}
		}
		
		Label lblToDo = new Label(shlSwtApplication, SWT.NONE);
		lblToDo.setBounds(10, 44, 81, 25);
		lblToDo.setText("To Do:");
		
		Button btnNewEvent = new Button(shlSwtApplication, SWT.NONE);
		btnNewEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//create an event as long as no more than than 10 buttons are on screen.
				if(buttons.count<=10) {
					Event event= new Event("", "", null);
				
					Label lblTitle = new Label(shlSwtApplication, SWT.NONE);
					lblTitle.setBounds(10, 428, 38, 25);
					lblTitle.setText("Title ");
				
					Label lblDate = new Label(shlSwtApplication, SWT.NONE);
					lblDate.setText("Date ");
					lblDate.setBounds(10, 462, 38, 25);
				
					Label lblImportance = new Label(shlSwtApplication, SWT.NONE);
					lblImportance.setText("Importance ------>");
					lblImportance.setBounds(10, 397, 153, 25);
				
					titleText = new Text(shlSwtApplication, SWT.BORDER);
					titleText.setBounds(48, 425, 98, 31);
				
					dateText= new Text(shlSwtApplication, SWT.BORDER);
					dateText.setBounds(48, 459, 98, 31);
				
					Button btnUrgent = new Button(shlSwtApplication, SWT.RADIO);
					btnUrgent.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							event.setImportance(Importance.URGENT);
						}
					});
					btnUrgent.setBounds(169, 402, 133, 25);
					btnUrgent.setText("Urgent");
				
					Button btnImportant = new Button(shlSwtApplication, SWT.RADIO);
					btnImportant.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							event.setImportance(Importance.IMPORTANT);
						}
					});
					btnImportant.setBounds(169, 428, 133, 25);
					btnImportant.setText("Important");
				
					Button btnDoWhenever = new Button(shlSwtApplication, SWT.RADIO);
					btnDoWhenever.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							event.setImportance(Importance.DO_WHENEVER);
						}
					});
					btnDoWhenever.setBounds(169, 456, 133, 25);
					btnDoWhenever.setText("Do Whenever");
				
					Button btnAddEvent = new Button(shlSwtApplication, SWT.NONE);
					btnAddEvent.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							titleCurrentText= titleText.getText();
							dateCurrentText= dateText.getText();
							event.setName(titleCurrentText);
							event.setDate(dateCurrentText);
							event.setUrgency();
							
							events.add(event);
						
						
							Button btnCheckButton = new Button(shlSwtApplication, SWT.CHECK);
							btnCheckButton.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseDown(MouseEvent e) {
									deleteAndAlign(btnCheckButton);
								}
							});
							btnCheckButton.setBounds(10, currentY, 433, 25);
							btnCheckButton.setText(event.urgency+","+event.getName()+", Do By: "+event.getDate());
							buttons.add(btnCheckButton);
						
							currentY+=30;
						
						
							lblTitle.dispose();
							lblDate.dispose();
							lblImportance.dispose();
							titleText.dispose();
							dateText.dispose();
							btnUrgent.dispose();
							btnImportant.dispose();
							btnDoWhenever.dispose();
							btnAddEvent.dispose();
							buttons.incrementCount();
						
					}
				});
				btnAddEvent.setBounds(338, 418, 105, 35);
				btnAddEvent.setText("Add Event");
				}
				
			}
		});
		btnNewEvent.setBounds(338, 10, 105, 35);
		btnNewEvent.setText("New Event");

	}
	
	public void deleteAndAlign(Button btnCheckButton) {
		ArrayList<Button> buttonsCurrent= buttons.getListButtons();
		String btnCurrentText=btnCheckButton.getText();
		Event deleteEvent=events.parseToEvent(btnCurrentText);
		events.remove(deleteEvent);
		currentY-=30;
		
		for(int i=0; i<buttonsCurrent.size()-1;i++) {
			
			Button btnCurrent=buttonsCurrent.get(i);
					
			if(btnCurrent.equals(btnCheckButton)) {
				for(int j=i; j<buttonsCurrent.size()-1;j++) {
					Button btnFuture= buttonsCurrent.get(j+1);
					int x= btnFuture.getBounds().x;
					int y= btnFuture.getBounds().y;
					int width= btnFuture.getBounds().width;
					int height= btnFuture.getBounds().height;
					btnFuture.setBounds(x, y-30, width, height);
					
				}
			}
		}

		buttons.decrementCount();
		buttons.remove(btnCheckButton);
		btnCheckButton.dispose();
	}
	
}
