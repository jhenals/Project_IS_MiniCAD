package MiniCAD.interpreter.utilExpr;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandExprIF;

public class TipoExpr implements CommandExprIF {
    private Token param;

    public TipoExpr(Token param) {
        this.param = param;
    }


    @Override
    public String toString() {
        return "TipoExpr{" +
                "oggetto=" + param +
                '}';
    }

    @Override
    public TokenType interpreta(Context context) {
        return param.getTipo();
    }
}
