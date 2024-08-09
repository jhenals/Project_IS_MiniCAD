package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.utilExpr.TipoExpr;
import MiniCAD.mvc.specificCmds.utilExpr.Token;
import MiniCAD.mvc.specificCmds.utilExpr.TokenType;
import MiniCAD.util.Util;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.List;
import java.util.Map;

public class ListCommand implements CommandExprIF<String> {
    private CommandExprIF<?> param;

    public ListCommand(CommandExprIF<?> param) {
        this.param = param;
    }

    @Override
    public String interpreta(Context context) {
        String res ;
        if( param instanceof TipoExpr){
            TokenType tokenType = (TokenType) param.interpreta(context);
            res = gestisceParamTipoExpr(tokenType, context);
        } else{
            Token token = (Token) param;
            res = gestisceParamToken(token, context);
        }
        return res;
    }

    private String gestisceParamToken(Token token, Context context) {
        StringBuilder sb = new StringBuilder();
        String t = token.interpreta(context);
        switch( token.getTipo() ) {
            case OBJ_ID -> {
                if (context.getObjectbyId(t) == null) {
                    return "Object doesn't exist";
                } else if (context.getObjectTypeById(t).equals("Group")) {
                    if (context.getObjectbyId(t) == null) {
                        return "Group doesn't exist";
                    } else {
                        GraphicObject object = context.getObjectbyId(t);
                        sb.append("Properties of group with id=").append(token.interpreta(context)).append("\n");
                        sb.append(" Type: ").append(object.getType()).append("\n");
                        sb.append(" Current Position: ").append(reformatPosizione(object)).append("\n");
                        sb.append(" Members:").append(context.getObjectIDsOfGroup(token.interpreta(context)));
                    }
                } else {
                    GraphicObject object = context.getObjectbyId(t);
                    sb.append("Properties of object with id=").append(t).append("\n");
                    sb.append(" Type: ").append(object.getType()).append("\n");
                    if (object.getType().equals("Circle")) {
                        sb.append(" Radius: ").append(((CircleObject) object).getRadius()).append("\n");
                    } else {
                        sb.append(" Dimension: base=").append(Util.formatDouble(object.getDimension().getWidth())).append(" height=").append(Util.formatDouble(object.getDimension().getHeight())).append("\n");
                    }
                    sb.append(" Current Position: ").append(reformatPosizione(object));
                }
            }

            case ALL -> {
                Map<String, GraphicObject> allObjects = context.getAllObjects();
                sb.append("List of all objects:\n");
                if (allObjects.isEmpty()) {
                    sb.append(" EMPTY");
                } else {
                    for (String id : allObjects.keySet()) {
                        sb.append(" ").append(id).append(" type ").append(allObjects.get(id).getType()).append(" in position:").append(reformatPosizione(allObjects.get(id))).append("\n");
                    }
                }
            }

            case GROUPS -> {
                sb.append("List of all groups:\n");
                Map<String, List<String>> allObjects = context.getAllGroups();
                if (allObjects.isEmpty()) {
                    sb.append(" EMPTY");
                } else {
                    for (String id : allObjects.keySet()) {
                        sb.append(" ").append(id).append(": ").append(allObjects.get(id));
                    }
                }
            }
        } //switch
        return sb.toString();
    }//gestisceToken

    private String gestisceParamTipoExpr(TokenType tokenType, Context context) {
        StringBuilder sb = new StringBuilder();
        switch (tokenType){
            case CIRCLE -> {
                Map<String, GraphicObject> circles= context.getObjectsByType("Circle");
                if(circles.isEmpty()){
                    sb.append(" EMPTY");
                }else {
                    sb.append("Objects of type CIRCLE:\n");
                    for (String id: circles.keySet()) {
                        sb.append(" ").append(id).append(" in position:").append(reformatPosizione(circles.get(id))).append("\n");
                    }
                }
            }

            case RECTANGLE -> {
                Map<String, GraphicObject> rectangles= context.getObjectsByType("Rectangle");
                sb.append("Objects of type RECTANGLE:\n");
                if(rectangles.isEmpty()){
                    sb.append(" EMPTY");
                }else {
                    for( String id : rectangles.keySet()  ){
                        sb.append(" ").append(id).append(" in position:").append(reformatPosizione(rectangles.get(id))).append("\n");
                    }
                }
            }

            case IMG -> {
                Map<String, GraphicObject> images= context.getObjectsByType("Image");
                sb.append("Objects of type IMAGE:\n");
                if(images.isEmpty()){
                    sb.append(" EMPTY");
                }else {
                    for( String id : images.keySet()  ){
                        sb.append(" ").append(id).append(" in position:").append(reformatPosizione(images.get(id))).append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }

    private String reformatPosizione(GraphicObject go) {
        return "(" + String.format("%.2f",go.getPosition().getX()) + ";" + String.format("%.2f",go.getPosition().getY())
                + ")";
    }

    @Override
    public String toString() {
        return "ListCommand{" +
                "parametro=" + param +
                '}';
    }
}
