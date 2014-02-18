package z80.modules.impls.io.screen;

import z80.modules.AbstractModule;

/**
 * Created by Joe on 18/02/2014.
 */
public class ScreenModule extends AbstractModule {


    private ScreenGUI screenGUI;

    public ScreenModule() {
        super();
        screenGUI = new ScreenGUI(840, 480);
    }

    @Override
    public String getName() {
        return "Screen";
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onActivate() {
        screenGUI.activate();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
