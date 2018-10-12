import java.util.Stack;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.lang.Runnable;

public class Controller
{
	private Stack<ReversibleCommand> undoStack;
	private Stack<ReversibleCommand> redoStack;
	private TimeModel time;
	private ArrayList<View> views;
	
	public Controller()
	{
		undoStack = new Stack<ReversibleCommand>();
		redoStack = new Stack<ReversibleCommand>();
		views = new ArrayList<View>();
		time = new TimeModel();
		ScheduledExecutorService ticker = Executors.newScheduledThreadPool(1);
		ticker.scheduleAtFixedRate(new Runnable()
		{
			@Override
			public void run()
			{
				time.getTime().add(Calendar.SECOND, 1);
				updateViews();
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	public TimeModel getModel()
	{
		return time;
	}
	
	public void setModel(TimeModel model)
	{
		time = model;
	}
	
	public void onReceiveCommand(Command command)
	{
		switch(command.getTargetClass().getName())
		{
			case "TimeModel":
			{
				command.assignTarget(time);
				break;
			}
			case "Controller":
			{
				command.assignTarget(this);
				break;
			}
			default:
			{
				throw new IllegalArgumentException("Unsupported class " + command.getTargetClass().getName());
			}
		}
		command.execute();
		if(command instanceof ReversibleCommand)
		{
			undoStack.push((ReversibleCommand)command);
			redoStack.clear();
		}
		updateViews();
	}
	
	public void tryUndo()
	{
		if(!undoStack.empty())
		{
			ReversibleCommand command = undoStack.pop();
			//Not all reversible commands will necessarily be undoable when we attempt to undo an action.
			if(command.reverse()) redoStack.push(command);
		}
	}
	
	public void tryRedo()
	{
		if(!redoStack.empty())
		{	
			ReversibleCommand command = redoStack.pop();
			command.execute();
			undoStack.push(command);
		}
	}
	
	public void addView(View view)
	{
		views.add(view);
	}
	
	public void removeView(View view)
	{
		views.remove(view);
	}
	
	public boolean findView(View view)
	{
		return views.contains(view);
	}
	
	public void updateViews()
	{
		Calendar now = time.getTime();
		for(int i=0; i<views.size(); i++)
		{
			views.get(i).update(now);
		}
	}
}