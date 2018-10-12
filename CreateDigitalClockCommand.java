public class CreateDigitalClockCommand extends ReversibleCommand
{
	private DigitalClockView v;
	
	public CreateDigitalClockCommand()
	{
		targetClass = Controller.class;
	}
	
	public void execute()
	{
		v = new DigitalClockView((Controller)target);
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