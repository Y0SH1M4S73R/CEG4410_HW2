import java.util.Calendar;
class SetYearCommand extends ReversibleCommand
{
	private int value;
	private boolean absolute;
	
	public SetYearCommand(int value)
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
			value -= calendar.get(Calendar.YEAR);
			absolute = false;
		}
		calendar.add(Calendar.YEAR, value);
	}
	
	public boolean reverse()
	{
		((TimeModel)target).getTime().add(Calendar.YEAR, -value);
		return true;
	}
}