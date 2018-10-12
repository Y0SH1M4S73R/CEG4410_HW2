public class UndoCommand extends Command
{
	public UndoCommand()
	{
		targetClass = Controller.class;
	}
	
	public void execute()
	{
		((Controller)target).tryUndo();
	}
}