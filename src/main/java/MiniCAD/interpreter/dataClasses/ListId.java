package MiniCAD.interpreter.dataClasses;

import java.util.ArrayList;
import java.util.List;

public class ListId {
    List<Token> ids = new ArrayList<>();

    public ListId(List<Token> ids) {
        this.ids = ids;
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
}
