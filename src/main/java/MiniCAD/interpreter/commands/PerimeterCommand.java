package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.Token;
import MiniCAD.util.Util;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.Map;

public class PerimeterCommand implements CommandIF {
    private Token param;

    public PerimeterCommand(Token param) {
        this.param = param;
    }

    @Override
    public String interpreta(Context context) {
        String res = "";
        String idStr = param.interpreta(context);
        switch (param.getTipo()){
            case OBJ_ID -> {
                if(context.getObjectTypeById(idStr) == "Group"){
                    Double perim = calcolaPerimDelGruppo(context, param.interpreta(context));
                    res =  "Area del gruppo con id="+ param.getValore().toString()+ ": " +Util.formatDouble(perim);
                }else {
                    Double perim = calcolaPerimDellOggetto(context, param.interpreta(context));
                    res = "Area dell'oggetto con id=" + param.getValore().toString() + ": " + Util.formatDouble(perim);
                }
            }
            case CIRCLE -> {
                Double perim= calcolaPerimDiTuttiCerchi(context);
                res =  "Area totale di tutti i cerchi: "+Util.formatDouble(perim);
            }
            case RECTANGLE -> {
                Double perim= calcolaPerimDiTuttiRettangoli(context);
                res = "Area totale di tutti i rettangoli: "+Util.formatDouble(perim);
            }
            case ALL -> {
                Double perim= calcolaPerimTotaleDiTuttiOggetti(context);
                res = "Area totale di tutti gli oggetti: "+ Util.formatDouble(perim);
            }
        }
        System.out.println(res);
        return res;
    }

    private Double calcolaPerimTotaleDiTuttiOggetti(Context context) {
        Double perim =0D;
        perim += calcolaPerimDiTuttiRettangoli(context);
        perim += calcolaPerimDiTuttiCerchi(context);
        return perim;
    }

    private Double calcolaPerimDelGruppo(Context context, String gid) {
        Double perim = 0D;
        Map<String, GraphicObject> graphicObjectList = context.getObjectsOfGroup(gid);
        for( String id: graphicObjectList.keySet() ){
            perim += calcolaPerimDellOggetto(context, id);
        }
        return perim;
    }

    private Double calcolaPerimDiTuttiRettangoli(Context context) {
        Double perim = 0D;
        Map<String, GraphicObject> cerchiMap = context.getObjectsByType("Rectangle");
        for (String id : cerchiMap.keySet() ){
            perim += calcolaPerimDellOggetto(context, id);
        }
        return perim;
    }

    private Double calcolaPerimDiTuttiCerchi(Context context) {
        Double perim = 0D;
        Map<String, GraphicObject> cerchiMap = context.getObjectsByType("Circle");
        for (String id : cerchiMap.keySet() ){
            perim += calcolaPerimDellOggetto(context, id);
        }
        return perim;
    }

    private Double calcolaPerimDellOggetto(Context context, String id) {
        Double perim = 0D;
        GraphicObject object = context.getObjectbyId(id);
        if (object.getType().equals("Circle")) {
            Double r = ((CircleObject) object).getRadius();
            perim = 2 * Math.PI * ((CircleObject) object).getRadius();
        } else if (object.getType().equals("Rectangle")) {
            Double l = object.getDimension().getHeight();
            Double w = object.getDimension().getWidth();
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


    @Override
    public boolean undo(Context context) {
        return false;
    }
}
