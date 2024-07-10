package MiniCAD.interpreter.utils;

import MiniCAD.interpreter.commands.Command;

public class Posizione {
    private float param1;
    private float param2;

    public Posizione(float param1, float param2){
        if( param1 >=0 && param2 >=0 ){
            this.param1 = param1;
            this.param2 = param2;
        }else{
            throw new IllegalArgumentException("Parametri devono essere positivi.");
        }
    }

    public Posizione(String param1, String param2){
        this.param1 = Float.parseFloat(param1);
        this.param2 = Float.parseFloat(param2);
    }

    public float getParam1() {
        return param1;
    }

    public void setParam1(float param1) {
        this.param1 = param1;
    }

    public float getParam2() {
        return param2;
    }

    public void setParam2(float param2) {
        this.param2 = param2;
    }

    @Override
    public String toString(){
        return "("+ param1 + ", "+ param2+ ")";
    }
}
