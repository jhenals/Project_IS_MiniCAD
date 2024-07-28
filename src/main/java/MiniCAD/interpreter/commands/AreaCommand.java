package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.Token;
import MiniCAD.util.Util;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.List;
import java.util.Map;

public class AreaCommand implements CommandIF {
    private Token param;

    public AreaCommand(Token param) {
        this.param = param;
    }


    @Override
    public String interpreta(Context context) {
        String res = "";
        String idStr = param.interpreta(context);
        switch (param.getTipo()){
            case OBJ_ID -> {
                if(context.getObjectTypeById(idStr) == "Group"){
                    Double perim = calcolaAreaDelGruppo(context, param.interpreta(context));
                    res =  "Area del gruppo con id="+ param.getValore().toString()+ ": " +Util.formatDouble(perim);
                }else {
                    Double perim = calcolaAreaDellOggetto(context, param.interpreta(context));
                    res = "Area dell'oggetto con id=" + param.getValore().toString() + ": " + Util.formatDouble(perim);
                }
            }
            case CIRCLE -> {
                Double area= calcolaAreaDiTuttiCerchi(context);
                res =  "Area totale di tutti i cerchi: "+Util.formatDouble(area);
            }
            case RECTANGLE -> {
                Double area= calcolaAreaDiTuttiRettangoli(context);
                res = "Area totale di tutti i rettangoli: "+Util.formatDouble(area);
            }
            case ALL -> {
                Double area= calcolaAreaTotaleDiTuttiOggetti(context);
                res = "Area totale di tutti gli oggetti: "+ Util.formatDouble(area);
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
        Double a = 0D;
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
