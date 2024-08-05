package MiniCAD.shapes.interpreter.utilExpr;

import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.commandsExpr.CommandExprIF;

import java.util.LinkedList;
import java.util.List;

public class ListIdExpr implements CommandExprIF {
    private List<Token> ids;

    public ListIdExpr(List<Token> ids) {
        this.ids = ids;
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
        if( ids.size()==1){
            return null;
        }else{
            for(Token id : ids ){
                if(!(idList.contains(id))){
                    idList.add(id.interpreta(context));
                }
            }
        }

        return idList;
    }
}
