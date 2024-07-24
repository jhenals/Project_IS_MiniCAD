package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.Token;
import MiniCAD.util.Util;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.List;

public class ListCommand implements Command{
    private Token parametro;

    public ListCommand(Token parametro) {
        this.parametro = parametro;
    }


    @Override
    public String interpreta() {
        String res = "";
        StringBuilder sb = new StringBuilder();
        ObjectManager objectManager = ObjectManager.getInstance();

        switch( parametro.getTipo() ){
            case OBJ_ID -> {
                if (objectManager.getObjectbyId(parametro.getValore().toString()) == null) {
                    res = "Oggetto non esiste";
                } else {
                    GraphicObject object = objectManager.getObjectbyId(parametro.getValore().toString());
                    sb.append("Elenco di proprietà dell'oggetto con id=" + parametro.getValore().toString() +"\n");
                    sb.append(" Tipo: " + object.getType()+"\n");
                    if( object.getType().equals("Circle")){
                        sb.append(" Raggio: " + ((CircleObject)object).getRadius() +"\n");
                    }else{
                        sb.append(" Dimensione: base=" + Util.formatDouble(object.getDimension().getWidth())+" altezza="+ Util.formatDouble(object.getDimension().getHeight())+"\n");
                    }
                    sb.append(" Posizione corrente: "+ stampaPosizione(object));
                }
            }
            case GRP_ID -> {
                if (objectManager.getObjectbyId(parametro.getValore().toString()) == null) {
                    res = "Gruppo non esiste";
                } else {
                    GraphicObject object = objectManager.getObjectbyId(parametro.getValore().toString());
                    sb.append("Proprietà del gruppo con id=" + parametro.getValore().toString() +"\n");
                    sb.append(" Tipo: " + object.getType()+"\n");
                    sb.append(" Posizione corrente del gruppo: " + stampaPosizione(object)+  "\n");
                    sb.append(" Oggetti:" + objectManager.getObjectIDsOfGroup(parametro.getValore().toString()));
                }
            }
            case CIRCLE -> {
                List<GraphicObject> circles= objectManager.getObjectsByType("Circle");
                if(circles.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    sb.append("Oggetti di tipo CERCHIO:\n");
                    for (GraphicObject go : circles) {
                        sb.append(" " + objectManager.getIdByObject(go) + " in posizione:" + stampaPosizione(go) + "\n");
                    }
                }
            }

            case RECTANGLE -> {
                List<GraphicObject> rectangles= objectManager.getObjectsByType("Rectangle");
                sb.append("Oggetti di tipo RETTANGOLO:\n");
                if(rectangles.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    for( GraphicObject go : rectangles ){
                        sb.append(" " + objectManager.getIdByObject(go) + " in posizione:" + stampaPosizione(go) +"\n");
                    }
                }
            }

            case IMG -> {
                List<GraphicObject> images= objectManager.getObjectsByType("Image");
                sb.append("Oggetti di tipo IMMAGINE:\n");
                if(images.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    for (GraphicObject go : images) {
                        sb.append(" " + objectManager.getIdByObject(go) + " in posizione:" + stampaPosizione(go) + "\n");
                    }
                }
            }

            case ALL -> {
                List<GraphicObject> allObjects= objectManager.getAllObjects();
                sb.append("Elenco di tutti gli oggetti:\n");
                if(allObjects.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    for (GraphicObject go : allObjects) {
                        sb.append(" " + objectManager.getIdByObject(go) + " in posizione:" + stampaPosizione(go) + "\n");
                    }
                }
            }

            case GROUPS -> {
                if( objectManager.getAllGroupIds().isEmpty()){
                    System.out.println("EMPTY");
                }else{
                    sb.append(objectManager.stampaGruppi());
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
                "parametro=" + parametro +
                '}';
    }
}
