import java.util.Calendar;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
public class DigitalClockView extends View
{
	@FXML private VBox root;
	@FXML private Text dateLine;
	@FXML private Text timeLine;

	public DigitalClockView(Controller cont)
	{
		super(cont);
		Stage newRep = new Stage();
		try
		{
			FXMLLoader ldr = new FXMLLoader(getClass().getResource("DigitalClockView.fxml"));
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
	
	public void update(Calendar c)
	{
		dateLine.setText(c.get(Calendar.MONTH)+1 + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR));
		timeLine.setText(((c.get(Calendar.HOUR)>0)?(c.get(Calendar.HOUR)):12) + ":" + String.format("%02d", c.get(Calendar.MINUTE)) + ":" + String.format("%02d", c.get(Calendar.SECOND)) + " " + c.getDisplayName(Calendar.AM_PM, Calendar.SHORT, new Locale("en")));
	}
}