package MiniCAD.interpreter;

import MiniCAD.interpreter.dataClasses.GroupObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.*;

//Singleton Design Pattern
public class ObjectManager {
    private static ObjectManager instance;
    private Map<String, GraphicObject> objects;
    private Map<String, List<String>> groups ;

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
        if (idExistsInAnyGroup(id)) {
            for (List<String> group : groups.values()) {
                group.remove(id);
            }
        }
    }

    public GraphicObject getObjectbyId(String id){
        return objects.get(id);
    }

    public String getIdByObject(GraphicObject go){
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
    public List<GraphicObject> getAllObjects(){
        return new ArrayList<>(objects.values());
    }

    public void addGroup(String grpId, List<String> objectIds){
        groups.put(grpId, new ArrayList<>(objectIds));
    }

    public void unGroup(String grpId){
        groups.remove(grpId);
    }

    public List<String> getAllGroupIds(){
        return new ArrayList<>(groups.keySet());
    }
    public List<String> getObjectIDsOfGroup(String grpID){
        List<String> res = new ArrayList<>();
        List<String> objIds = groups.get(grpID);
            for( String id : objIds ){
                res.add(id);
            }
        return res;
    }
    public Map<String, GraphicObject> getObjectsOfGroup(String grpId){
        Map<String, GraphicObject> res = new HashMap<>();
        List<String> group = groups.get(grpId);
        if( !group.isEmpty() ){
            for(String objId : group ){
                GraphicObject obj = objects.get(objId);
                if(obj != null )
                    res.put(objId, obj);
            }
        }
        return res;
    }

    public boolean idExistsInAnyGroup(String objId){
        for (List<String> group : groups.values()) {
            if (group.contains(objId)) {
                return true;
            }
        }
        return false;
    }


    public String stampaGruppi(){
        StringBuilder sb = new StringBuilder();
        sb.append("Groups:\n");
        if(groups.isEmpty()){
            sb.append("EMPTY");
        }else{
            for(Map.Entry<String, List<String>> entry : groups.entrySet()  ){
                sb.append(" ").append(entry.getKey()).append(":").append(entry.getValue().toString()).append("\n");
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
        sb.append(stampaGruppi());

        return sb.toString();
    }

    /*
    Manages the state and lifecycle of graphic objects.

     you can use the Singleton pattern to create the ObjectManager class. The Singleton pattern ensures that a class has only one instance and provides a global point of access to it. This can be useful for the ObjectManager class, which manages the state and lifecycle of drawable objects in your miniCAD project.
     */
}
