package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.utils.Token;
import MiniCAD.interpreter.utils.TokenType;
import MiniCAD.util.GeneratoreId;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.security.Key;
import java.util.List;
import java.util.Map;

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
                GraphicObject object = objectManager.getObject(parametro.getValore().toString());
                System.out.println("Oggetto con id " + parametro.getValore().toString());
                System.out.println("Tipo:" + object.getType());
                System.out.println("Dimensione" + object.getDimension());
                System.out.println("Posizione corrente" + object.getPosition());
            }
            case CIRCLE -> {
                List<GraphicObject> circles= objectManager.getObjectsByType("Circle");
                StringBuilder sb= new StringBuilder();
                sb.append("Oggetti di tipo CERCHIO:\n");
                for( GraphicObject go : circles ){
                    sb.append(objectManager.getKey(go) + " in posizione:" + go.getPosition().toString());
                }
            }

            case RECTANGLE -> {
                List<GraphicObject> rectangles= objectManager.getObjectsByType("Rectangle");
                StringBuilder sb= new StringBuilder();
                sb.append("Oggetti di tipo RETTANGOLO:\n");
                for( GraphicObject go : rectangles ){
                    sb.append(objectManager.getKey(go) + " in posizione:" + go.getPosition().toString());
                }
            }

            case IMG -> {
                List<GraphicObject> images= objectManager.getObjectsByType("Image");
                StringBuilder sb= new StringBuilder();
                sb.append("Oggetti di tipo IMMAGINE:\n");
                for( GraphicObject go : images ){
                    sb.append(objectManager.getKey(go) + " in posizione:" + go.getPosition().toString());
                }
            }

            case ALL -> {
                List<GraphicObject> allObjects= objectManager.getAllObjects();
                StringBuilder sb= new StringBuilder();
                sb.append("Elenco di tutti gli oggetti:\n");
                for( GraphicObject go : allObjects ){
                    sb.append(objectManager.getKey(go) + " in posizione:" + go.getPosition().toString());
                }
            }

            case GROUPS -> {
                if( objectManager.getAllGroupsKeys().isEmpty()){
                    System.out.println("EMPTY");
                }else{
                    objectManager.groupsString();
                }
            }


        }
    }


    @Override
    public String toString() {
        return "ListCommand{" +
                "parametro=" + parametro +
                '}';
    }
}
