package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;

public class ScaleCommand implements  Command{
    private Token objectId;
    private Token dimensione;

    public ScaleCommand(Token objectId, Token dimensione) {
        this.objectId = objectId;
        this.dimensione = dimensione;
    }

    public Token getObjectId() {
        return objectId;
    }

    public void setObjectId(Token objectId) {
        this.objectId = objectId;
    }

    public Token getDimensione() {
        return dimensione;
    }

    public void setDimensione(Token dimensione) {
        this.dimensione = dimensione;
    }

    @Override
    public void interpreta() {

    }

    @Override
    public String toString() {
        return "ScaleCommand{" +
                "objectId=" + objectId +
                ", dimensione=" + dimensione +
                '}';
    }
}
