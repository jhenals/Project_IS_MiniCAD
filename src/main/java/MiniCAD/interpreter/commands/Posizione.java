package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;
import MiniCAD.interpreter.lexerparser.TokenType;

public class Posizione implements Command {
    private Token param1;
    private Token param2;

    public Posizione(Token param1, Token param2){
        if( param1.getTipo() == TokenType.POS_FLOAT && param2.getTipo() == TokenType.POS_FLOAT){
            this.param1 = param1;
            this.param2 = param2;
        }
    }

    public Token getParam1() {
        return param1;
    }

    public void setParam1(Token param1) {
        this.param1 = param1;
    }

    public Token getParam2() {
        return param2;
    }

    public void setParam2(Token param2) {
        this.param2 = param2;
    }

    @Override
    public void interpreta() {
        System.out.println("("+ param1 + "; "+ param2+ ")");

    }

    @Override
    public String toString(){
        return "("+ param1.getValore() + ", "+ param2.getValore()+ ")";
    }
}
