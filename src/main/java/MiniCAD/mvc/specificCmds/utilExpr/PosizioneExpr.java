package MiniCAD.mvc.specificCmds.utilExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.commandsExpr.CommandExprIF;

public class PosizioneExpr implements CommandExprIF<PosizioneExpr> {
    private Token p1;
    private Token p2;
    private float param1;
    private float param2;

    public PosizioneExpr(Token param1, Token param2) {
        p1 = param1;
        p2 = param2;
    }
        /*
        if( param1 >=0 && param2 >=0 ){
            this.param1 = param1;
            this.param2 = param2;
        }else{
            throw new IllegalArgumentException("Parametri devono essere positivi.");
        }


    }

    public PosizioneExpr(String param1, String param2){
        this.param1 = Float.parseFloat(param1);
        this.param2 = Float.parseFloat(param2);
    }

         */
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
