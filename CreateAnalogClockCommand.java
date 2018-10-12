public class CreateAnalogClockCommand extends ReversibleCommand
{
	private AnalogClockView v;
	
	public CreateAnalogClockCommand()
	{
		targetClass = Controller.class;
	}
	
	public void execute()
	{
		v = new AnalogClockView((Controller)target);
		((Controller)target).addView(v);
		v.getRepresentation().show();
	}
	
	public boolean reverse()
	{
		if(((Controller)target).findView(v))
		{
			v.getRepresentation().close();
			return true;
		}
		return false;
	}
}