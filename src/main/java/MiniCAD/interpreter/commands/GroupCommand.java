package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand implements  Command{
    private ListId objectIds ;
    //Ciascuno di essi può essere l’identificativo di un oggetto o di un gruppo.

    public GroupCommand(ListId objectIds) {
        this.objectIds = objectIds;

    }

    public ListId getObjectIds() {
        return objectIds;
    }

    public void setObjectIds(ListId objectIds) {
        this.objectIds = objectIds;
    }

    @Override
    public void interpreta() {
        //TODO
        // Il comando visualizza l’identificativo generato per il gruppo creato.

    }

    @Override
    public String toString() {
        return "GroupCommand{" +
                "objectIds=" + objectIds.toString() +
                '}';
    }
}
