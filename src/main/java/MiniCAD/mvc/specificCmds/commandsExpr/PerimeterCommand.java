package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.utilExpr.TipoExpr;
import MiniCAD.mvc.specificCmds.utilExpr.Token;
import MiniCAD.mvc.specificCmds.utilExpr.TokenType;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.HashMap;
import java.util.Map;

public class PerimeterCommand implements CommandExprIF<String> {
    private CommandExprIF<?> param;

    public PerimeterCommand(CommandExprIF<?> param) {
        this.param = param;
    }

    @Override
    public String interpreta(Context context) {
        String res;
        if( param instanceof TipoExpr ){
            TokenType tokenType = ((TipoExpr) param).interpreta(context);
            switch (tokenType){
                case CIRCLE -> {
                    double perim= calcolaPerimDiTipi("Circle",context);
                    res =  String.valueOf(perim);
                }
                case RECTANGLE -> {
                    double perim= calcolaPerimDiTipi("Rectangle",context);
                    res =String.valueOf(perim);
                }
                case IMG -> {
                    double perim= calcolaPerimDiTipi("Image",context);
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
        perim += calcolaPerimDiTipi("Rectangle",context);
        perim += calcolaPerimDiTipi("Image",context);
        perim += calcolaPerimDiTipi("Circle",context);
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

    private double calcolaPerimDiTipi( String tipo ,Context context) {
        double perim = 0D;
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
