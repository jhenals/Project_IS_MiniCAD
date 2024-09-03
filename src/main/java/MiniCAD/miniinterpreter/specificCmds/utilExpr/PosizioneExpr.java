package MiniCAD.miniinterpreter.specificCmds.utilExpr;

import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.commandsExpr.CommandExprIF;

public class PosizioneExpr implements CommandExprIF<PosizioneExpr> {
    private Token p1;
    private Token p2;
    private float param1;
    private float param2;

    public PosizioneExpr(Token param1, Token param2) {
        p1 = param1;
        p2 = param2;
    }

    @Override
    public String toString(){
        return "("+ param1 + ", "+ param2+ ")";
    }

    @Override
    public PosizioneExpr interpreta(Context context) {
        param1 = Float.parseFloat(p1.interpreta(context));
        param2 = Float.parseFloat(p2.interpreta(context));
        return this ;
    }

    public float getParam1() {
        return param1;
    }

    public float getParam2() {
        return param2;
    }

}
