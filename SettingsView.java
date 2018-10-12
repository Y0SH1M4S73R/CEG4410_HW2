import java.util.function.UnaryOperator;
import java.util.Calendar;
import java.time.LocalDate;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.application.Platform;

public class SettingsView extends View
{
	@FXML private VBox root;
	@FXML private TextField yearField;
	@FXML private TextField monthField;
	@FXML private TextField dayField;
	@FXML private TextField hourField;
	@FXML private Button hourAMToggle;
	private boolean am_or_pm; //AM is false, PM is true
	@FXML private TextField minuteField;
	@FXML private TextField secondField;
	
	public static UnaryOperator<Change> integerFilter(int minVal, int maxVal)
	{
		return change -> {
				String text = change.getControlNewText();
				if(text.isEmpty())
				{
					return change;
				}
				if(text.matches("[0-9]*"))
				{
					if(Integer.parseInt(text)>maxVal)
					{
						String newText = Integer.toString(maxVal);
						change.setRange(0, change.getControlText().length());
						change.setText(newText);
						change.setCaretPosition(change.getControlNewText().length());
					}
					else if(Integer.parseInt(text)<minVal)
					{
						String newText = Integer.toString(minVal);
						change.setRange(0, change.getControlText().length());
						change.setText(newText);
						change.setCaretPosition(change.getControlNewText().length());
					}
					return change;
				}
				return null;
			};
	}
	
	public SettingsView()
	{
		
	}
	
	public SettingsView(Controller cont)
	{
		super(cont);
		am_or_pm = false;
		Stage newRep = new Stage();
		try
		{
			FXMLLoader ldr = new FXMLLoader(getClass().getResource("SettingsView.fxml"));
			ldr.setController(this);
			ldr.load();
			newRep.setScene(new Scene(root));
			yearField.setTextFormatter(new TextFormatter<String>(integerFilter(Integer.MIN_VALUE, Integer.MAX_VALUE)));
			monthField.setTextFormatter(new TextFormatter<String>(integerFilter(1,12)));
			dayField.setTextFormatter(new TextFormatter<String>(integerFilter(1,31)));
			hourField.setTextFormatter(new TextFormatter<String>(integerFilter(1,12)));
			minuteField.setTextFormatter(new TextFormatter<String>(integerFilter(0,59)));
			secondField.setTextFormatter(new TextFormatter<String>(integerFilter(0,59)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		setRepresentation(newRep);
		getRepresentation().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {System.exit(0);});
	}
	
	@FXML
	public void onYearEntered(Event e)
	{
		if(!(yearField.getText().isEmpty()))
		{
			issueCommand(new SetYearCommand(Integer.parseInt(yearField.getText())));
			yearField.setText("");
		}
	}
	
	@FXML
	public void onMonthEntered(Event e)
	{
		if(!(monthField.getText().isEmpty()))
		{
			issueCommand(new SetMonthCommand(Integer.parseInt(monthField.getText())));
			monthField.setText("");
		}
	}
	
	@FXML
	public void onDayEntered(Event e)
	{
		if(!(dayField.getText().isEmpty()))
		{
			issueCommand(new SetDayCommand(Integer.parseInt(dayField.getText())));
			dayField.setText("");
		}
	}
	
	@FXML
	public void onAMPMToggle(Event e)
	{
		am_or_pm = !am_or_pm;
		hourAMToggle.setText(am_or_pm? "P.M." : "A.M.");
	}
	
	@FXML
	public void onHourEntered(Event e)
	{
		if(!(hourField.getText().isEmpty()))
		{
			issueCommand(new SetHourCommand((Integer.parseInt(hourField.getText())%12)+(am_or_pm?12:0)));
			hourField.setText("");
		}
	}
	
	@FXML
	public void onMinuteEntered(Event e)
	{
		if(!(minuteField.getText().isEmpty()))
		{
			issueCommand(new SetMinuteCommand(Integer.parseInt(minuteField.getText())));
			minuteField.setText("");
		}
	}
	
	@FXML
	public void onSecondEntered(Event e)
	{
		if(!(secondField.getText().isEmpty()))
		{
			issueCommand(new SetSecondCommand(Integer.parseInt(secondField.getText())));
			secondField.setText("");
		}
	}
	
	@FXML
	public void onUndo(Event e)
	{
		issueCommand(new UndoCommand());
	}
	
	@FXML
	public void onRedo(Event e)
	{
		issueCommand(new RedoCommand());
	}
	
	@FXML
	public void requestDigital(Event e)
	{
		issueCommand(new CreateDigitalClockCommand());
	}
	
	@FXML
	public void requestAnalog(Event e)
	{
		issueCommand(new CreateAnalogClockCommand());
	}
	
	public void update(Calendar c)
	{
		dayField.setTextFormatter(new TextFormatter<String>(integerFilter(1,c.getActualMaximum(Calendar.DAY_OF_MONTH))));
	}
}