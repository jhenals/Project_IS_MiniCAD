package MiniCAD.interpreter.utilExpr;

import MiniCAD.interpreter.commands.CommandIF;
import MiniCAD.interpreter.Context;

import java.util.LinkedList;
import java.util.List;

public class ListId implements CommandIF {
    private List<Token> ids = new LinkedList<>();

    public ListId(Token id) {
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

    @Override
    public List<String> interpreta(Context context) {
        List<String> idList = new LinkedList<>();
        for(Token id : ids ){
            idList.add(id.interpreta(context));
        }
        return idList;
    }
}
