package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.TipoExpr;
import MiniCAD.interpreter.utilExpr.Token;
import MiniCAD.interpreter.utilExpr.TokenType;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.Map;

public class PerimeterCommand implements CommandExprIF {
    private CommandExprIF param;

    public PerimeterCommand(CommandExprIF param) {
        this.param = param;
    }

    @Override
    public String interpreta(Context context) {
        String res;
        if( param instanceof TipoExpr ){
            TokenType tokenType = ((TipoExpr) param).interpreta(context);
            switch (tokenType){
                case CIRCLE -> {
                    double perim= calcolaPerimDiTuttiCerchi(context);
                    res =  String.valueOf(perim);
                }
                case RECTANGLE -> {
                    double perim= calcolaPerimDiTuttiRettangoli(context);
                    res =String.valueOf(perim);
                }
                case IMG -> {
                    double perim= calcolaPerimDiTuttiImmagini(context);
                    res =String.valueOf(perim);
                }
                default -> throw new IllegalArgumentException("Tipo di oggetto sconosciuto");
            }
        }else{
            Token token = (Token) param;
            String idStr = token.interpreta(context);
            switch (token.getTipo()){
                case OBJ_ID -> {
                    if(context.getObjectTypeById(idStr).equals( "Group") ){
                        double perim = calcolaPerimDelGruppo(context, idStr);
                        res = String.valueOf(perim);
                    }else {
                        double perim = calcolaPerimDellOggetto(context, idStr);
                        res = String.valueOf(perim);
                    }
                }

                case ALL -> {
                    double perim= calcolaPerimTotaleDiTuttiOggetti(context);
                    res = String.valueOf(perim);
                }
                default -> throw new IllegalArgumentException("Token sconosciuto");
            }
        }
        System.out.println(res);
        return res;
    }

    private Double calcolaPerimTotaleDiTuttiOggetti(Context context) {
        double perim =0D;
        perim += calcolaPerimDiTuttiRettangoli(context);
        perim += calcolaPerimDiTuttiCerchi(context);
        return perim;
    }

    private Double calcolaPerimDelGruppo(Context context, String gid) {
        double perim = 0D;
        Map<String, GraphicObject> graphicObjectList = context.getObjectsOfGroup(gid);
        for( String id: graphicObjectList.keySet() ){
            perim += calcolaPerimDellOggetto(context, id);
        }
        return perim;
    }
    private double calcolaPerimDiTuttiImmagini(Context context) {
        double perim = 0D;
        Map<String, GraphicObject> cerchiMap = context.getObjectsByType("Image");
        for (String id : cerchiMap.keySet() ){
            perim += calcolaPerimDellOggetto(context, id);
        }
        return perim;
    }

    private Double calcolaPerimDiTuttiRettangoli(Context context) {
        double perim = 0D;
        Map<String, GraphicObject> cerchiMap = context.getObjectsByType("Rectangle");
        for (String id : cerchiMap.keySet() ){
            perim += calcolaPerimDellOggetto(context, id);
        }
        return perim;
    }

    private Double calcolaPerimDiTuttiCerchi(Context context) {
        double perim = 0D;
        Map<String, GraphicObject> cerchiMap = context.getObjectsByType("Circle");
        for (String id : cerchiMap.keySet() ){
            perim += calcolaPerimDellOggetto(context, id);
        }
        return perim;
    }

    private Double calcolaPerimDellOggetto(Context context, String id) {
        double perim = 0D;
        GraphicObject object = context.getObjectbyId(id);
        if (object.getType().equals("Circle")) {
            double r = ((CircleObject) object).getRadius();
            perim = 2 * Math.PI * r;
        } else if (object.getType().equals("Rectangle")) {
            double l = object.getDimension().getHeight();
            double w = object.getDimension().getWidth();
            perim = 2 * l + 2 * w;
        }else if (object.getType().equals("Image")) {
            double l = object.getDimension().getHeight();
            double w = object.getDimension().getWidth();
            perim = 2 * l + 2 * w;
        }
        return perim;
    }


    @Override
    public String toString() {
        return "PerimeterCommand{" +
                "parametro=" + param +
                '}';
    }



}
