package z80.modules.impls.assembler;

import z80.modules.AbstractModule;

/**
 * Created by Joe on 08/02/2014.
 */
public class AssemblerModule extends AbstractModule {

    AssemblerGUI assemblerGUI;

    public AssemblerModule() {
        this.name = "AssemblerModule";
        this.active = false;
        assemblerGUI = new AssemblerGUI();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onActivate() {
        this.active = true;
        assemblerGUI.activate();
    }

    @Override
    public void onDisable() {
        this.active = false;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }
}
