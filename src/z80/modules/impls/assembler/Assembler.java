package z80.modules.impls.assembler;

/**
 * Created by Joe on 08/02/2014.
 */
public class Assembler {

    public Assembler() {

    }

    public String assemble(String assembly) {

        StringBuilder binary;
        String[] instructions = assembly.split("\n");

        for(int i =0; i<instructions.length; i++) {
            if(instructions[i].startsWith(InstructionType.LD.getType())) {
                
            }
        }

        return "";
    }
}
