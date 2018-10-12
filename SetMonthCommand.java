import java.util.Calendar;
class SetMonthCommand extends ReversibleCommand
{
	private int value;
	private boolean absolute;
	
	public SetMonthCommand(int value)
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
			value -= (1 + calendar.get(Calendar.MONTH));
			absolute = false;
		}
		calendar.add(Calendar.MONTH, value);
	}
	
	public boolean reverse()
	{
		((TimeModel)target).getTime().add(Calendar.MONTH, -value);
		return true;
	}
}