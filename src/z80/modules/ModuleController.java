package z80.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Joe on 08/02/2014.
 */
public class ModuleController {

    private HashMap<String, Module> modules;

    public ModuleController() {
        modules = new HashMap<String, Module>();
        discoverModules();
    }

    private String addModule(Module module) {
        modules.put(module.getName(), module);

        return module.getName();
    }

    public void clockModules() {
        for(int i=0; i<modules.size(); i++) {
            modules.get(i).onUpdate();
        }
    }

    public Module getModule(String name) {
        return modules.get(name);
    }

    private void discoverModules() {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader()
                .getResourceAsStream("modules")));
        String line;
        try {
            while((line = in.readLine()) != null) {
                if(!line.trim().isEmpty() && !line.startsWith("#")) {
                    Module m = (Module) this.getClass().getClassLoader().loadClass(line).newInstance();
                    addModule(m);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object[] getKeyArray() {
        return modules.keySet().toArray();
    }
}
