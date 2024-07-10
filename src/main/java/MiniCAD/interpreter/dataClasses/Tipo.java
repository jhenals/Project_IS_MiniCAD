package MiniCAD.interpreter.dataClasses;

import MiniCAD.interpreter.commands.Command;

public class Tipo implements Command {
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
