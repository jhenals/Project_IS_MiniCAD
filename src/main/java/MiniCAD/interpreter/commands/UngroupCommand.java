package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;
import MiniCAD.interpreter.lexerparser.TokenType;

public class UngroupCommand implements  Command{
    private Token groupId;

    public UngroupCommand(Token groupId) {
        if( groupId.getTipo() == TokenType.OBJ_ID ){
            this.groupId = groupId;
        }else{
            throw new IllegalArgumentException("Parametro passato non è un tipo OBJ_ID");
        }
    }

    public Token getGroupId() {
        return groupId;
    }

    public void setGroupId(Token groupId) {
        this.groupId = groupId;
    }

    @Override
    public void interpreta() {
        //TODO
    }

    @Override
    public String toString() {
        return "UngroupCommand{" +
                "groupId=" + groupId +
                '}';
    }
}
