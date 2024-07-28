package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.Token;
import MiniCAD.util.Util;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.List;
import java.util.Map;

public class ListCommand implements CommandIF {
    private Token token;

    public ListCommand(Token t) {
        this.token = t;
    }


    @Override
    public String interpreta(Context context) {
        String res = "";
        String idStr = token.interpreta(context);
        StringBuilder sb = new StringBuilder();

        switch( token.getTipo() ){
            case OBJ_ID -> {
                if (context.getObjectbyId(token.interpreta(context)) == null) {
                    res = "Oggetto non esiste";
                } else if (context.getObjectTypeById(idStr) == "Group" ) {
                    if (context.getObjectbyId(token.getValore().toString()) == null) {
                        res = "Gruppo non esiste";
                    } else {
                        GraphicObject object = context.getObjectbyId(token.getValore().toString());
                        sb.append("Proprietà del gruppo con id=" + token.interpreta(context) +"\n");
                        sb.append(" Tipo: " + object.getType()+"\n");
                        sb.append(" Posizione corrente del gruppo: " + stampaPosizione(object)+  "\n");
                        sb.append(" Oggetti:" + context.getObjectIDsOfGroup(token.interpreta(context)));
                    }
                }else{
                    GraphicObject object = context.getObjectbyId(token.getValore().toString());
                    sb.append("Elenco di proprietà dell'oggetto con id=" + token.getValore().toString() +"\n");
                    sb.append(" Tipo: " + object.getType()+"\n");
                    if( object.getType().equals("Circle")){
                        sb.append(" Raggio: " + ((CircleObject)object).getRadius() +"\n");
                    }else{
                        sb.append(" Dimensione: base=" + Util.formatDouble(object.getDimension().getWidth())+" altezza="+ Util.formatDouble(object.getDimension().getHeight())+"\n");
                    }
                    sb.append(" Posizione corrente: "+ stampaPosizione(object));
                }
            }
            case CIRCLE -> {
                Map<String, GraphicObject> circles= context.getObjectsByType("Circle");
                if(circles.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    sb.append("Oggetti di tipo CERCHIO:\n");
                    for (String id: circles.keySet()) {
                        sb.append(" " + id + " in posizione:" + stampaPosizione(circles.get(id)) + "\n");
                    }
                }
            }

            case RECTANGLE -> {
                Map<String, GraphicObject> rectangles= context.getObjectsByType("Rectangle");
                sb.append("Oggetti di tipo RETTANGOLO:\n");
                if(rectangles.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    for( String id : rectangles.keySet()  ){
                        sb.append(" " + id + " in posizione:" + stampaPosizione(rectangles.get(id)) + "\n");
                    }
                }
            }

            case IMG -> {
                Map<String, GraphicObject> images= context.getObjectsByType("Image");
                sb.append("Oggetti di tipo IMMAGINE:\n");
                if(images.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    for( String id : images.keySet()  ){
                        sb.append(" " + id + " in posizione:" + stampaPosizione(images.get(id)) + "\n");
                    }
                }
            }

            case ALL -> {
                Map<String, GraphicObject> allObjects= context.getAllObjects();
                sb.append("Elenco di tutti gli oggetti:\n");
                if(allObjects.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    for (String id : allObjects.keySet()) {
                        sb.append(" " + id + " in posizione:" + stampaPosizione(allObjects.get(id)) + "\n");
                    }
                }
            }

            case GROUPS -> {
                sb.append("Elenco di tutti i gruppi:\n");
                Map<String, List<String>> allObjects= context.getAllGroups();
                if( allObjects.isEmpty()){
                    System.out.println(" EMPTY");
                }else{
                    for( String id : allObjects.keySet() ){
                        sb.append(" "+id+": " + allObjects.get(id));
                    }
                }
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private String stampaPosizione(GraphicObject go) {
        return "(" + String.format("%.2f",go.getPosition().getX()) + ";" + String.format("%.2f",go.getPosition().getY())
                + ")";
    }


    @Override
    public String toString() {
        return "ListCommand{" +
                "parametro=" + token +
                '}';
    }
}
