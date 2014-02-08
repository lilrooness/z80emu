package z80.modules;

/**
 * Created by Joe on 08/02/2014.
 */
public interface Module {

    public String getName();

    public void onClockCycle();

    public void onActivate();

    public void onDisable();

    public boolean isActive();

}
