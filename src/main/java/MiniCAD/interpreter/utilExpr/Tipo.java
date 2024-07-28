package MiniCAD.interpreter.utilExpr;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandExprIF;

public class Tipo implements CommandExprIF {
    private Token oggetto;

    public Tipo(Token oggetto) {
        this.oggetto = oggetto;
    }


    @Override
    public String toString() {
        return "Tipo{" +
                "oggetto=" + oggetto +
                '}';
    }

    @Override
    public TokenType interpreta(Context context) {
        return oggetto.getTipo();
    }
}
