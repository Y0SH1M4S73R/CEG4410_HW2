import javafx.stage.Stage;
import java.util.Calendar;

public abstract class View
{
	private Stage representation;
	private Controller controller;
	public abstract void update(Calendar time);
	public View()
	{
		
	}
	public View(Controller cont)
	{
		controller = cont;
	}
	public void setRepresentation(Stage representation)
	{
		this.representation = representation; 
	}
	public Stage getRepresentation()
	{
		return representation;
	}
	public void issueCommand(Command c)
	{
		controller.onReceiveCommand(c);
	}
	public Controller getController()
	{
		return controller;
	}
}