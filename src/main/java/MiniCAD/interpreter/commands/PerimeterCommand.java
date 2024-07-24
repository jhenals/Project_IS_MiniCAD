package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.Token;
import MiniCAD.util.Util;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.List;
import java.util.Map;

public class PerimeterCommand implements  Command{
    private Token param;
    ObjectManager objectManager = ObjectManager.getInstance();

    public PerimeterCommand(Token param) {
        this.param = param;
    }

    @Override
    public String interpreta() {
        String res = "";
        switch (param.getTipo()){
            case OBJ_ID -> {
                Double perim= calcolaPerimetroDellOggetto(param.getValore().toString());
                res = "Perimetro dell'oggetto con id="+ param.getValore().toString()+ ": "+ Util.formatDouble(perim);
            }
            case GRP_ID -> {
                Double perim= calcolaPerimetroDelGruppo(param.getValore().toString());
                res = "Perimetro del gruppo con id="+ param.getValore().toString()+ ": "+ Util.formatDouble(perim);
            }
            case CIRCLE -> {
                Double perim= calcolaPerimetroDiTuttiCerchi();
                res = "Circonferenza totale di tutti i cerchi: "+ Util.formatDouble(perim);
            }
            case RECTANGLE -> {
                Double perim= calcolaPerimetroDiTuttiRettangoli();
                res = "Perimetro totale di tutti i rettangoli: "+ Util.formatDouble(perim);
            }
            case ALL -> {
                Double perim= calcolaPerimetroTotaleDiTuttiOggetti();
                res = "Perimetro totale: "+ Util.formatDouble(perim);
            }
        }
        System.out.println(res);
        return res;
    }

    private Double calcolaPerimetroDelGruppo(String gid) {
        Double perim = 0D;
        Map<String, GraphicObject> graphicObjectList = objectManager.getObjectsOfGroup(gid);
        for( GraphicObject go : graphicObjectList.values() ){
            if(go.getType().equals("Circle")){
                perim += calcolaPerimetroDellOggetto(objectManager.getIdByObject(go));
            }else if(go.getType().equals("Rectangle")){
                perim += calcolaPerimetroDellOggetto(objectManager.getIdByObject(go));
            }
        }
        return perim;
    }

    private Double calcolaPerimetroTotaleDiTuttiOggetti() {
        Double perim = 0D;
        perim += calcolaPerimetroDiTuttiRettangoli();
        perim += calcolaPerimetroDiTuttiCerchi();
        return perim;
    }


    private Double calcolaPerimetroDiTuttiRettangoli() {
        Double perim = 0D;
        List<GraphicObject> rects= objectManager.getObjectsByType("Rectangle");
        for (GraphicObject go : rects ){
            perim += calcolaPerimetroDellOggetto(objectManager.getIdByObject(go));
        }
        return perim;
    }

    private Double calcolaPerimetroDiTuttiCerchi() {
        Double perim = 0D;
        List<GraphicObject> cerchi= objectManager.getObjectsByType("Circle");
        for (GraphicObject go : cerchi ){
            perim += calcolaPerimetroDellOggetto(objectManager.getIdByObject(go));
        }
        return perim;
    }

    private Double calcolaPerimetroDellOggetto(String id) {
        Double perim = 0D;
        GraphicObject object = objectManager.getObjectbyId(id);
        if(object.getType().equals("Circle")){
            perim= 2*Math.PI*((CircleObject)object).getRadius();
        } else if (object.getType().equals("Rectangle")) {
            Double l = object.getDimension().getHeight();
            Double w = object.getDimension().getWidth();
            perim = 2*l+2*w;
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
