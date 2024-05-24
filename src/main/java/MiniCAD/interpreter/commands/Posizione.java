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
    @Override
    public void interpreta() {
        System.out.println("("+ param1 + "; "+ param2+ ")");

    }

    @Override
    public String toString(){
        return "("+ param1 + "; "+ param2+ ")";
    }
}
