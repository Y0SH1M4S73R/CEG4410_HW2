import java.util.Calendar;
class SetSecondCommand extends ReversibleCommand
{
	private int value;
	private boolean absolute;
	
	public SetSecondCommand(int value)
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
			value -= calendar.get(Calendar.SECOND);
			absolute = false;
		}
		calendar.add(Calendar.SECOND, value);
	}
	
	public boolean reverse()
	{
		((TimeModel)target).getTime().add(Calendar.SECOND, -value);
		return true;
	}
}