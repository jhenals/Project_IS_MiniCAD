package MiniCAD.shapes.interpreter.utilExpr;

import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.commandsExpr.CommandExprIF;

public class Token implements CommandExprIF {
    private TokenType tipo;
    private Object valore;

    public Token( TokenType tipo, Object valore){
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
