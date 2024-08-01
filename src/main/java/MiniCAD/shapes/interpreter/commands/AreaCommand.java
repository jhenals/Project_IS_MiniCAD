package MiniCAD.shapes.interpreter.commands;

import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.utilExpr.TipoExpr;
import MiniCAD.shapes.interpreter.utilExpr.Token;
import MiniCAD.shapes.interpreter.utilExpr.TokenType;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.Map;

public class AreaCommand implements CommandExprIF {
    private CommandExprIF param;

    public AreaCommand(CommandExprIF param) {
        this.param = param;
    }


    @Override
    public String interpreta(Context context) {
        String res;
        if( param instanceof TipoExpr){
            TokenType tokenType = ((TipoExpr) param).interpreta(context);
            switch (tokenType){
                case CIRCLE -> {
                    Double area= calcolaAreaDiTuttiCerchi(context);
                    res = String.valueOf(area);
                }
                case RECTANGLE -> {
                    Double area= calcolaAreaDiTuttiRettangoli(context);
                    res = String.valueOf(area);
                }
                case IMG -> {
                    Double area= calcolaAreaDiTuttiImmagini(context);
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



    private Double calcolaAreaTotaleDiTuttiOggetti(Context context) {
        Double area =0D;
        area += calcolaAreaDiTuttiRettangoli(context);
        area += calcolaAreaDiTuttiCerchi(context);
        return area;
    }

    private Double calcolaAreaDelGruppo(Context context, String gid) {
        Double area = 0D;
        Map<String, GraphicObject> graphicObjectList = context.getObjectsOfGroup(gid);
        for( String id: graphicObjectList.keySet() ){
            area += calcolaAreaDellOggetto(context, id);
        }
        return area;
    }

    private Double calcolaAreaDiTuttiImmagini(Context context) {
        Double area = 0D;
        Map<String, GraphicObject> cerchiMap = context.getObjectsByType("Image");
        for (String id : cerchiMap.keySet() ){
            area += calcolaAreaDellOggetto(context, id);
        }
        return area;
    }

    private Double calcolaAreaDiTuttiRettangoli(Context context) {
        Double area = 0D;
        Map<String, GraphicObject> cerchiMap = context.getObjectsByType("Rectangle");
        for (String id : cerchiMap.keySet() ){
            area += calcolaAreaDellOggetto(context, id);
        }
        return area;
    }

    private Double calcolaAreaDiTuttiCerchi(Context context) {
        Double area = 0D;
        Map<String, GraphicObject> cerchiMap = context.getObjectsByType("Circle");
        for (String id : cerchiMap.keySet() ){
            area += calcolaAreaDellOggetto(context, id);
        }
        return area;
    }

    private Double calcolaAreaDellOggetto(Context context, String id) {
        double a = 0D;
        GraphicObject object = context.getObjectbyId(id);
        if(object.getType().equals("Circle")){
            Double r = ((CircleObject)object).getRadius();
            a= Math.PI*r*r;
        } else if (object.getType().equals("Rectangle")) {
            Double l = object.getDimension().getHeight();
            Double w = object.getDimension().getWidth();
            a = l*w;
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