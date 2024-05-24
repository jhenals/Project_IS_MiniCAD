package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;

public class Tipo implements Command{
    private Token oggetto;

    public Tipo(Token oggetto) {
        this.oggetto = oggetto;
    }

    public Token getOggetto() {
        return oggetto;
    }

    public void setOggetto(Token oggetto) {
        this.oggetto = oggetto;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString() {
        return "Tipo{" +
                "oggetto=" + oggetto +
                '}';
    }
}
