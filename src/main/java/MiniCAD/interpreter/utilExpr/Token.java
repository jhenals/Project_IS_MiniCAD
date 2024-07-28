package MiniCAD.interpreter.utilExpr;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandIF;

public class Token implements CommandIF {
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

    public void setTipo(TokenType tipo){
        this.tipo = tipo;
    }

    public void setValore(String valore){
        this.valore = valore;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + tipo +
                ", value=" + valore +
                '}';
    }

    @Override
    public boolean undo(Context context) {
        return false;
    }

    @Override
    public String interpreta(Context context) {
        return valore.toString();
    }
}
