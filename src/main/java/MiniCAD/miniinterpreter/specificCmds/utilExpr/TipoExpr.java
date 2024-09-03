package MiniCAD.miniinterpreter.specificCmds.utilExpr;

import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.commandsExpr.CommandExprIF;

public class TipoExpr implements CommandExprIF<TokenType> {
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
