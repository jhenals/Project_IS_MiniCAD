package MiniCAD.interpreter.utilExpr;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandExprIF;

public class PosizioneExpr implements CommandExprIF {
    private float param1;
    private float param2;

    public PosizioneExpr(float param1, float param2){
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

    public float getParam1() {
        return param1;
    }

    public float getParam2() {
        return param2;
    }

    @Override
    public String toString(){
        return "("+ param1 + ", "+ param2+ ")";
    }

    @Override
    public PosizioneExpr interpreta(Context context) {
        return this ;
    }
}
