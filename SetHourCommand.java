import java.util.Calendar;
class SetHourCommand extends ReversibleCommand
{
	private int value;
	private boolean absolute;
	
	public SetHourCommand(int value)
	{
		this.value = value;
		absolute = true;
		targetClass = TimeModel.class;
	}
	
	public void setAbsolute(boolean absolute)
	{
		this.absolute = absolute;
	}
	
	public void execute()
	{
		Calendar calendar = ((TimeModel)target).getTime();
		if(absolute)
		{
			value -= calendar.get(Calendar.HOUR_OF_DAY);
			absolute = false;
		}
		calendar.add(Calendar.HOUR_OF_DAY, value);
	}
	
	public boolean reverse()
	{
		((TimeModel)target).getTime().add(Calendar.HOUR_OF_DAY, -value);
		return true;
	}
}