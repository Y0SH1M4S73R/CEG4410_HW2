import java.lang.Class;
import java.lang.IllegalArgumentException;
public abstract class Command
{
	protected Class targetClass;
	protected Object target;
	public Class getTargetClass()
	{
		return targetClass;
	}
	public void assignTarget(Object o)
	{
		if(targetClass.isInstance(o))
		{
			target = o;
		}
		else
		{
			throw new IllegalArgumentException(o.toString() + " is not an instance of " + targetClass.getName());
		}
	}
	public abstract void execute();
}