import javafx.application.Application;
import javafx.stage.Stage;

public class HW2 extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage thisArgumentIsIgnored) {
        Controller cont = new Controller();
		SettingsView window = new SettingsView(cont);
		cont.addView(window);
		window.getRepresentation().show();
	}
}