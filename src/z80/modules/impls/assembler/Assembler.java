package z80.modules.impls.assembler;

import z80.modules.AbstractModule;

/**
 * Created by Joe on 08/02/2014.
 */
public class Assembler extends AbstractModule {

    public Assembler() {
        this.name = "Assembler";
        this.active = false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void onClockCycle() {

    }

    @Override
    public void onActivate() {
        this.active = true;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean isActive() {
        return this.active;
    }
}
