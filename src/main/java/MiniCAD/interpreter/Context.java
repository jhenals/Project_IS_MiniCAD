package MiniCAD.interpreter;

import MiniCAD.interpreter.utilExpr.GroupObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {
    private Map<String, GraphicObject> objects ;
    private Map<String, List<String>> groups ;
    private int nextId = 0;

    public Context(){
        objects = new HashMap<>();
        groups = new HashMap<>();
    }

    public String generaId(){
        return "id"+ (nextId++);
    }

    public void addObject(String id, GraphicObject go){
        objects.put(id, go);
    }

    public void removeObjectById(String id){
        objects.remove(id);
    }

    public GraphicObject getObjectbyId(String id) {
        return objects.get(id);
    }

    public GroupObject createGroup(List<String> idList) {
        String gid = generaId();
        GroupObject group = new GroupObject(gid);
        Map<String, GraphicObject> objs = new HashMap<>();
        for( String id: idList){
            objs.put(id, objects.get(id));
        }
        group.setObjects(objs);
        objects.put(gid, group);
        groups.put(gid, idList);
        return group;
    }

    public Map<String, GraphicObject> getObjectsOfGroup(String gid) {
        Map<String, GraphicObject> ret = new HashMap<>();
        List<String> idObjectsInGroup = groups.get(gid);
        for( String id : idObjectsInGroup ){
            ret.put(id, objects.get(id));
        }
        return ret;
    }


    public Map<String, GraphicObject> getObjectsByType(String tipo) {
        Map<String, GraphicObject> ret = new HashMap<>();
        for( String id : objects.keySet()){
            if( objects.get(id).getType().equals(tipo) )
                ret.put(id, objects.get(id));
        }
        return ret;
    }

    public List<String> getObjectIDsOfGroup(String gid) {
        return groups.get(gid);
    }

    public Map<String, GraphicObject> getAllObjects(){
        return objects;
    }

    public Map<String, List<String>> getAllGroups() {
        return groups;
    }

    public String getObjectTypeById(String id){
        return objects.get(id).getType();
    }

    public void unGroup(String gid) {
        groups.remove(gid);
    }
}
