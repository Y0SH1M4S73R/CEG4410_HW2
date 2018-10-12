import java.util.Calendar;

public class TimeModel
{
	private Calendar time;
	
	public TimeModel()
	{
		time = Calendar.getInstance();
	}
	
	public Calendar getTime()
	{
		return time;
	}
}