package MiniCAD.interpreter.dataClasses;

import MiniCAD.interpreter.commands.Command;

public class Tipo{
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
    public String toString() {
        return "Tipo{" +
                "oggetto=" + oggetto +
                '}';
    }
}
