package MiniCAD.mvc.specificCmds.utilExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.commandsExpr.CommandExprIF;

public class Token implements CommandExprIF<String> {
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
