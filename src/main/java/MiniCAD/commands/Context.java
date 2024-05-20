package MiniCAD.commands;

import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, GraphicObject> objects = new HashMap<>();

    public void addObject(String name, GraphicObject obj){
        objects.put(name, obj);
    }

    public GraphicObject getObject(String name){
        return objects.get(name);
    }
}
