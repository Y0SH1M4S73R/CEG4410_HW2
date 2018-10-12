import java.util.Calendar;
class SetMinuteCommand extends ReversibleCommand
{
	private int value;
	private boolean absolute;
	
	public SetMinuteCommand(int value)
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
			value -= calendar.get(Calendar.MINUTE);
			absolute = false;
		}
		calendar.add(Calendar.MINUTE, value);
	}
	
	public boolean reverse()
	{
		((TimeModel)target).getTime().add(Calendar.MINUTE, -value);
		return true;
	}
}