package MiniCAD.interpreter;

import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.*;

//Singleton Design Pattern
public class ObjectManager {
    private static ObjectManager instance;
    private Map<String, GraphicObject> objects;
    private Map<String, Map<String, GraphicObject>> groups ;

    private ObjectManager(){
        objects = new HashMap<>();
        groups = new HashMap<>();
    }

    public static ObjectManager getInstance(){
        if ( instance == null ){
            synchronized (ObjectManager.class){
                if(instance == null ){
                    instance = new ObjectManager();
                }
            }
        }
        return instance;
    }
    public void addObject(String id, GraphicObject obj){
        objects.put(id, obj);
    }

    public void removeObject(String id){
        objects.remove(id);
    }


    public GraphicObject getObject(String id){
        return objects.get(id);
    }

    public String getKeyByValue(GraphicObject go){
        for(Map.Entry<String, GraphicObject> entry : objects.entrySet() ){
            if( go.equals(entry.getValue()) ){
                return entry.getKey();
            }
        }
        return null;
    }
    public List<GraphicObject> getObjectsByType(String type){
        List<GraphicObject> graphicObjectList = new ArrayList<>();
        for(GraphicObject obj : objects.values() ){
            if( obj.getType().equals(type)){
                graphicObjectList.add(obj);
            }
        }
        return graphicObjectList;
    }

    public List<GraphicObject>getAllObjects(){
        return new ArrayList<>(objects.values());
    }

    public List<String> getAllGroupsKeys(){
        return new ArrayList<>(groups.keySet());
    }
    public Map<String, GraphicObject> getValuesOfGroup(String grpID){
        return groups.get(grpID);
    }

    public String groupsString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Groups:\n");
        if(groups.isEmpty()){
            sb.append("EMPTY");
        }else{
            for(Map.Entry<String, Map<String, GraphicObject>> entry : groups.entrySet()  ){
                sb.append(" ").append(entry.getKey()).append(entry.getValue().keySet()).append("\n");
            }
        }
        return sb.toString();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Object Manager:\n");
        sb.append("-----------------------------------\n");
        sb.append("Objects:\n");
        if(objects.isEmpty()){
            sb.append("EMPTY");
        }else {
            for (Map.Entry<String, GraphicObject> entry : objects.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue().getType()).append("\n");
            }
        }
        sb.append("-----------------------------------\n");
        sb.append("Groups:\n");
        if(groups.isEmpty()){
            sb.append("EMPTY");
        }else{
            for(Map.Entry<String, Map<String, GraphicObject>> entry : groups.entrySet()  ){
                sb.append(" ").append(entry.getKey()).append(entry.getValue().keySet()).append("\n");
            }
        }

        return sb.toString();
    }

    /*
    Manages the state and lifecycle of graphic objects.

     you can use the Singleton pattern to create the ObjectManager class. The Singleton pattern ensures that a class has only one instance and provides a global point of access to it. This can be useful for the ObjectManager class, which manages the state and lifecycle of drawable objects in your miniCAD project.
     */
}
