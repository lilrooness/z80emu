package z80.instructions.set;

import z80.core.RegisterState;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 27/11/2013
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class ExTransfer {
    public static int EXDEHL() {
        RegisterState registerState = RegisterState.getInstance();
        byte[] temp = registerState.getHl();
        registerState.setHl(registerState.getDe());
        registerState.setDe(temp);
        return 1;
    }


}
