package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.Token;
import MiniCAD.util.Util;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.List;
import java.util.Map;

public class AreaCommand implements  Command {
    private Token param;
    ObjectManager objectManager = ObjectManager.getInstance();


    public AreaCommand(Token param) {
        this.param = param;
    }

    public Token getParam() {
        return param;
    }

    public void setParam(Token param) {
        this.param = param;
    }


    @Override
    public String interpreta() {
        String res = "";
        switch (param.getTipo()){
            case OBJ_ID -> {
                Double area= calcolaAreaDellOggetto(param.getValore().toString());
                res = "Area dell'oggetto con id="+ param.getValore().toString() + ": "+ Util.formatDouble(area);
            }
            case GRP_ID -> {
                Double area = calcolaAreaDelGruppo(param.getValore().toString());
                res =  "Area del gruppo con id="+ param.getValore().toString()+ ": " +Util.formatDouble(area);
            }
            case CIRCLE -> {
                Double area= calcolaAreaDiTuttiCerchi();
                res =  "Area totale di tutti i cerchi: "+Util.formatDouble(area);
            }
            case RECTANGLE -> {
                Double area= calcolaAreaDiTuttiRettangoli();
                res = "Area totale di tutti i rettangoli: "+Util.formatDouble(area);
            }
            case ALL -> {
                Double area= calcolaAreaTotaleDiTuttiOggetti();
                res = "Area totale di tutti gli oggetti: "+ Util.formatDouble(area);
            }
        }
        return res;
    }

    private Double calcolaAreaTotaleDiTuttiOggetti() {
        Double area =0D;
        area += calcolaAreaDiTuttiRettangoli();
        area += calcolaAreaDiTuttiCerchi();
        return area;
    }

    private Double calcolaAreaDelGruppo(String gid) {
        Double perim = 0D;
        Map<String, GraphicObject> graphicObjectList = objectManager.getObjectsOfGroup(gid);
        for( GraphicObject go : graphicObjectList.values() ){
            if(go.getType().equals("Circle")){
                perim += calcolaAreaDellOggetto(objectManager.getIdByObject(go));
            }else if(go.getType().equals("Rectangle")){
                perim += calcolaAreaDellOggetto(objectManager.getIdByObject(go));
            }
        }
        return perim;
    }

    private Double calcolaAreaDiTuttiRettangoli() {
        Double area = 0D;
        List<GraphicObject>  rects= objectManager.getObjectsByType("Rectangle");
        for (GraphicObject go : rects ){
            area += calcolaAreaDellOggetto(objectManager.getIdByObject(go));
        }
        return area;
    }

    private Double calcolaAreaDiTuttiCerchi() {
        Double area = 0D;
        List<GraphicObject> cerchi= objectManager.getObjectsByType("Circle");
        for (GraphicObject go : cerchi ){
            area += calcolaAreaDellOggetto(objectManager.getIdByObject(go));
        }
        return area;
    }

    private Double calcolaAreaDellOggetto(String id) {
        Double a = 0D;
        GraphicObject object = objectManager.getObjectbyId(id);
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
