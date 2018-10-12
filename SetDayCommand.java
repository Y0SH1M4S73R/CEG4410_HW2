import java.util.Calendar;
class SetDayCommand extends ReversibleCommand
{
	private int value;
	private boolean absolute;
	
	public SetDayCommand(int value)
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
			value -= calendar.get(Calendar.DAY_OF_MONTH);
			absolute = false;
		}
		calendar.add(Calendar.DAY_OF_MONTH, value);
	}
	
	public boolean reverse()
	{
		((TimeModel)target).getTime().add(Calendar.DAY_OF_MONTH, -value);
		return true;
	}
}