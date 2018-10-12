import java.util.Calendar;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
public class AnalogClockView extends View
{
	@FXML private StackPane root;
	@FXML private Text dateLine;
	@FXML private Line hourHand;
	@FXML private Line minuteHand;
	@FXML private Line secondHand;

	public AnalogClockView(Controller cont)
	{
		super(cont);
		Stage newRep = new Stage();
		try
		{
			FXMLLoader ldr = new FXMLLoader(getClass().getResource("AnalogClockView.fxml"));
			ldr.setController(this);
			ldr.load();
			newRep.setScene(new Scene(root));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		setRepresentation(newRep);
		getRepresentation().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {getController().removeView(this);});
	}
	
	public static void setHandPosition(Line hand, double length, double unit, double unitMax)
	{
		hand.setEndX(length * Math.cos(((unit/unitMax)*2*Math.PI)-(Math.PI/2)));
		hand.setEndY(length * Math.sin(((unit/unitMax)*2*Math.PI)-(Math.PI/2)));
		hand.setTranslateX(length/2 * Math.cos(((unit/unitMax)*2*Math.PI)-(Math.PI/2)));
		hand.setTranslateY(length/2 * Math.sin(((unit/unitMax)*2*Math.PI)-(Math.PI/2)));
	}
	
	public void update(Calendar c)
	{
		dateLine.setText(c.get(Calendar.MONTH)+1 + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR));
		setHandPosition(hourHand, 60, (c.get(Calendar.HOUR)*3600+c.get(Calendar.MINUTE)*60+c.get(Calendar.SECOND)), 12*3600);
		setHandPosition(minuteHand, 120, (c.get(Calendar.MINUTE)*60+c.get(Calendar.SECOND)), 3600);
		setHandPosition(secondHand, 120, c.get(Calendar.SECOND), 60);
	}
}