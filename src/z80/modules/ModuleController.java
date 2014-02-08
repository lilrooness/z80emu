package z80.modules;

import java.util.HashMap;

/**
 * Created by Joe on 08/02/2014.
 */
public class ModuleController {

    private HashMap<String, Module> modules;
//    private ArrayList<String> moduleNames;

    public ModuleController() {
        modules = new HashMap<String, Module>();
    }

    public String addModule(Module module) {
        modules.put(module.getName(), module);

        return module.getName();
    }

    public void clockModules() {
        for(int i=0; i<modules.size(); i++) {
            modules.get(i).onClockCycle();
        }
    }

    public Module getModule(String name) {
        return modules.get(name);
    }
}
