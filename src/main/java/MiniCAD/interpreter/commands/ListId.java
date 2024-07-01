package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;
import MiniCAD.interpreter.lexerparser.TokenType;

import java.util.List;

public class ListId implements  Command{
    List<Token> ids;

    public ListId(List<Token> ids) {
        for ( Token t : ids )
            this.ids.add(t);
    }

    public void aggiungoId(Token id){
        if( id.getTipo() == TokenType.OBJ_ID )
            ids.add(id);
    }

    public List<Token> getIds() {
        return ids;
    }

    public void setIds(List<Token> ids) {
        for ( Token t: ids )
            this.ids.add(t);
    }

    @Override
    public void interpreta() {
    }

    @Override
    public void undo() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of ids: (");
        int cont = 0;
        for ( Token t : ids ){
            sb.append(t.getValore() );
            cont++;
            sb.append( (cont == ids.size() ? ')' : ',') );
        }
        return sb.toString();
    }
}
