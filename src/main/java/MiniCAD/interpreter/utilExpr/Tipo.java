package MiniCAD.interpreter.utilExpr;

import MiniCAD.interpreter.commands.CommandIF;
import MiniCAD.interpreter.Context;

public class Tipo implements CommandIF {
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
