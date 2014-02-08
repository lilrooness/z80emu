package z80.modules.impls.assembler;

/**
 * Created by Joe on 08/02/2014.
 */
public enum InstructionType {
    LD("LD"), PUSH("PUSH"), POP("POP"), EX("EX"), LDR("LDR"), LDIR("LDIR"), LDD("LDD"), LDDR("LDDR"), CPI("CPI"),
    CPIR("CPIR"), CPD("CPD"), CPDR("CPDR"), ADD("ADD"), ADC("ADC"), SUB("SUB"), AND("AND"), OR("OR"), XOR("XOR"),
    CP("CP"), INC("INC"), DEC("DEC"), DAA("DAA"), CPL("CPL"), NEG("NEG"),CCF("CCF"), SCF("SCF"), NOP("NOP"),
    HALT("HALT"), DI("DI"), EI("EI"), IM("IM"), SBC("SBC"), RLCA("RLCA"), RLA("RLA"), RRCA("RRCA"), RRA("RRA"),
    RLC("RLC"), RL("RL"), RRC("RRC"), RR("RR"), SLA("SLA"), SRA("SRA"), SRL("SRL"), RLD("RLD"), RRD("RRD"), BIT("BIT"),
    SET("SET"), RES("RES"), JC("JC"), JR("JR"), DJNZ("DJNZ"), CALL("CALL"), RET("RET"), RETI("RETI"), RETN("RETN"),
    RSTP("RSTP");

    private String type;

    private InstructionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
