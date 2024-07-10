package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.Token;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.List;

public class ListCommand implements Command{
    private Token parametro;

    public ListCommand(Token parametro) {
        this.parametro = parametro;
    }

    public Token getParametro() {
        return parametro;
    }

    public void setParametro(Token parametro) {
        this.parametro = parametro;
    }

    //TODO
    @Override
    public void interpreta() {
        ObjectManager objectManager = ObjectManager.getInstance();

        switch( parametro.getTipo() ){
            case OBJ_ID -> {
                if (objectManager.getObjectbyId(parametro.getValore().toString()) == null) {
                    System.out.println("Oggetto non esiste");
                } else {
                    GraphicObject object = objectManager.getObjectbyId(parametro.getValore().toString());
                    System.out.println("Elenco di proprietà dell'oggetto con id=" + parametro.getValore().toString());
                    System.out.println(" Tipo: " + object.getType());
                    System.out.println(" Dimensione: " + object.getDimension());
                    System.out.println(" Posizione corrente: " + stampaPosizione(object));
                }
            }
            case CIRCLE -> {
                List<GraphicObject> circles= objectManager.getObjectsByType("Circle");
                StringBuilder sb= new StringBuilder();
                if(circles.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    sb.append("Oggetti di tipo CERCHIO:\n");
                    for (GraphicObject go : circles) {
                        sb.append(" " + objectManager.getIdByObject(go) + " in posizione:" + stampaPosizione(go) + "\n");
                    }
                }
                System.out.println(sb.toString());
            }

            case RECTANGLE -> {
                List<GraphicObject> rectangles= objectManager.getObjectsByType("Rectangle");
                StringBuilder sb= new StringBuilder();
                sb.append("Oggetti di tipo RETTANGOLO:\n");
                if(rectangles.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    for( GraphicObject go : rectangles ){
                        sb.append(" " + objectManager.getIdByObject(go) + " in posizione:" + stampaPosizione(go) +"\n");
                    }
                }
                System.out.println(sb.toString());
            }

            case IMG -> {
                List<GraphicObject> images= objectManager.getObjectsByType("Image");
                StringBuilder sb= new StringBuilder();
                sb.append("Oggetti di tipo IMMAGINE:\n");
                if(images.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    for (GraphicObject go : images) {
                        sb.append(" " + objectManager.getIdByObject(go) + " in posizione:" + stampaPosizione(go) + "\n");
                    }
                }
                System.out.println(sb.toString());
            }

            case ALL -> {
                List<GraphicObject> allObjects= objectManager.getAllObjects();
                StringBuilder sb= new StringBuilder();
                sb.append("Elenco di tutti gli oggetti:\n");
                if(allObjects.isEmpty()){
                    sb.append(" VUOTO");
                }else {
                    for (GraphicObject go : allObjects) {
                        sb.append(" " + objectManager.getIdByObject(go) + " in posizione:" + stampaPosizione(go) + "\n");
                    }
                }
                System.out.println(sb.toString());
            }

            case GROUPS -> {
                if( objectManager.getAllGroupIds().isEmpty()){
                    System.out.println("EMPTY");
                }else{
                    objectManager.stampaGruppi();
                }
            }


        }
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
