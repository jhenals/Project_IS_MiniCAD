package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.utilExpr.TipoExpr;
import MiniCAD.mvc.specificCmds.utilExpr.Token;
import MiniCAD.mvc.specificCmds.utilExpr.TokenType;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.HashMap;
import java.util.Map;

public class AreaCommand implements CommandExprIF<String>{
    private CommandExprIF<?> param;

    public AreaCommand(CommandExprIF<?> param) {
        this.param = param;
    }


    @Override
    public String interpreta(Context context) {
        String res;
        if( param instanceof TipoExpr){
            TokenType tokenType = ((TipoExpr) param).interpreta(context);
            switch (tokenType){
                case CIRCLE -> {
                    Double area= calcolaAreaDiTipo("Circle", context);
                    res = String.valueOf(area);
                }
                case RECTANGLE -> {
                    Double area= calcolaAreaDiTipo("Rectangle", context);
                    res = String.valueOf(area);
                }
                case IMG -> {
                    Double area= calcolaAreaDiTipo("Image", context);
                    res = String.valueOf(area);
                }
                default -> throw new IllegalArgumentException("Tipo di oggetto sconosciuto");
            }
        }else{
            Token token = (Token) param;
            String idStr = token.interpreta(context);
            switch (token.getTipo()){
                case OBJ_ID -> {
                    if(context.getObjectTypeById(idStr).equals("Group")){
                        Double area = calcolaAreaDelGruppo(context, idStr);
                        res =  String.valueOf(area);
                    }else {
                        Double area = calcolaAreaDellOggetto(context, idStr);
                        res =String.valueOf(area);
                    }
                }

                case ALL -> {
                    Double area= calcolaAreaTotaleDiTuttiOggetti(context);
                    res = String.valueOf(area);
                }
                default -> throw new IllegalArgumentException("Token sconosciuto");
            }
        }
        System.out.println(res);
        return res;
    }



    private double calcolaAreaTotaleDiTuttiOggetti(Context context) {
        double area =0D;
        area += calcolaAreaDiTipo("Rectangle", context);
        area += calcolaAreaDiTipo("Circle", context);
        area += calcolaAreaDiTipo("Image", context);
        return area;
    }

    private double calcolaAreaDelGruppo(Context context, String gid) {
        double area = 0D;
        Map<String, GraphicObject> graphicObjectList = context.getObjectsOfGroup(gid);
        for( String id: graphicObjectList.keySet() ){
            area += calcolaAreaDellOggetto(context, id);
        }
        return area;
    }

    private double calcolaAreaDiTipo(String tipo, Context context) {
        double area = 0D;
        Map<String, GraphicObject> map = new HashMap<>();
        switch (tipo){
            case "Image" ->
                 map = context.getObjectsByType("Image");
            case "Circle" ->
                map = context.getObjectsByType("Circle");
            case "Rectangle" ->
                map = context.getObjectsByType("Rectangle");
        }
        for (String id : map.keySet() ){
            area += calcolaAreaDellOggetto(context, id);
        }
        return area;
    }

    private double calcolaAreaDellOggetto(Context context, String id) {
        double a = 0D;
        GraphicObject object = context.getObjectbyId(id);
        if(object.getType().equals("Circle")){
            Double r = ((CircleObject)object).getRadius();
            a= Math.PI*r*r;
        } else if (object.getType().equals("Rectangle")) {
            Double l = object.getDimension().getHeight();
            Double w = object.getDimension().getWidth();
            a = l*w;
        } else if (object.getType().equals("Image")) {
            Double l = object.getDimension().getHeight();
            Double w = object.getDimension().getWidth();
            a = l * w;
        }
        return a;
    }

    @Override
    public String toString() {
        return "AreaCommand{" +
                "param=" + param +
                '}';
    }
}
