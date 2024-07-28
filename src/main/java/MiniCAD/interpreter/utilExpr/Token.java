package MiniCAD.interpreter.utilExpr;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandExprIF;

public class Token<T> implements CommandExprIF {
    private TokenType tipo;
    private T valore;

    public Token( TokenType tipo, T valore){
        this.tipo = tipo;
        this.valore = valore;
    }

    public TokenType getTipo(){
        return tipo;
    }

    public Object getValore(){
        return valore;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + tipo +
                ", value=" + valore +
                '}';
    }

    @Override
    public String interpreta(Context context) {
        return valore.toString();
    }
}
