public class RedoCommand extends Command
{
	public RedoCommand()
	{
		targetClass = Controller.class;
	}
	
	public void execute()
	{
		((Controller)target).tryRedo();
	}
}