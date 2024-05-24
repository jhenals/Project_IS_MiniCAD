package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;

public class MoveOffCommand implements  Command{
    private Token objectId;
    private Posizione posizione;

    public MoveOffCommand(Token objectId, Posizione posizione) {
        this.objectId = objectId;
        this.posizione = posizione;
    }

    public Token getObjectId() {
        return objectId;
    }

    public void setObjectId(Token objectId) {
        this.objectId = objectId;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    @Override
    public void interpreta() {
        //logica di moveoff
    }

    @Override
    public String toString() {
        return "MoveOffCommand{" +
                "objectId=" + objectId +
                ", posizione=" + posizione +
                '}';
    }
}
