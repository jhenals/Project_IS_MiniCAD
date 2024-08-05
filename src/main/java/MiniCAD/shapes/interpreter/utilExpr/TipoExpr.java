package MiniCAD.shapes.interpreter.utilExpr;

import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.commandsExpr.CommandExprIF;

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
