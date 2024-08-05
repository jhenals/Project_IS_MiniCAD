package MiniCAD.shapes.interpreter;

import MiniCAD.shapes.model.GroupObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.specificcommand.NewObjectCmd;
import ObserverCommandFlyweight.is.shapes.view.GraphicObjectPanel;

import java.awt.geom.Point2D;
import java.util.*;

public class Context {
    private final Map<String, GraphicObject> objects ;
    private final Map<String, List<String>> groups ;
    private GraphicObjectPanel panel;
    private int nextId = 0;

    private final Map<String, Stack<Point2D>> oldPositions;
    public Context(GraphicObjectPanel gpanel){
        objects = new HashMap<>();
        groups = new HashMap<>();
        oldPositions = new HashMap<>();
        panel = gpanel;
    }
    public Context(){
        objects = new HashMap<>();
        groups = new HashMap<>();
        oldPositions = new HashMap<>();
    }

    public String generaId(){
        return "id"+ (nextId++);
    }

    public void addObject(String id, GraphicObject go){
        objects.put(id, go);
        new NewObjectCmd(panel, go).doIt();
    }

    public void removeObjectById(String id){
        GraphicObject go = objects.get(id) ;
        new NewObjectCmd(panel, go).undoIt();
        objects.remove(id);
        oldPositions.remove(id);
    }

    public GraphicObject getObjectbyId(String id) {
        return objects.get(id);
    }

    public GroupObject createGroup(List<String> idList) {
        String gid = generaId();
        String existingGrp= null;
        GroupObject group = new GroupObject(gid);
        boolean flag = false;
        List<String> mem= null;


        Map<String, GraphicObject> objs = new HashMap<>();
        for( String id: idList){
            if(objects.get(id).getType().equals("Group")){
                flag = true;
                existingGrp = id;
                List<String> groupObjs = groups.get(existingGrp);

                for ( String idMembers : groupObjs ){
                    objs.put(idMembers, objects.get(id));
                }
                mem = groupObjs;
            }else{
                objs.put(id, objects.get(id));
            }
        }
        group.setObjects(objs);
        objects.remove(existingGrp);
        groups.remove(existingGrp);
        objects.put(gid, group);

        if( flag ) {
            idList.remove(existingGrp);
            idList.addAll(mem);
        }
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


    public void moveOffObject(String idStr, Point2D offset) {
        GraphicObject go = objects.get(idStr);
        Point2D currentPosition = go.getPosition();

        double newX = currentPosition.getX() + offset.getX();
        double newY = currentPosition.getY() + offset.getY();

        go.moveTo(new Point2D.Double(newX, newY));
    }

    public void moveObject(String idStr, Point2D pos) {
        GraphicObject go = objects.get(idStr);
        oldPositions.computeIfAbsent(idStr, k -> new Stack<>()).push(go.getPosition());
        go.moveTo(pos);
    }

    public void ridimensiona(String idStr, String scaleFactor) {
        GraphicObject go = objects.get(idStr);
        go.scale(Double.parseDouble(scaleFactor));
    }

    public String getIdByClickedObject(GraphicObject go) {
        for( Map.Entry<String, GraphicObject> entry : objects.entrySet()){
            if( entry.getValue().getType().equals(go.getType()) &&
                    entry.getValue().contains(go.getPosition()) )
                return entry.getKey();
        }
        return null;
    }

    public void undoScale(String id, String zFactor) {
        GraphicObject go = objects.get(id) ;
        go.scale(1.0 / Double.parseDouble(zFactor));
    }

    public void undoMove(String id) {
        GraphicObject go = objects.get(id);
        Stack<Point2D> positions = oldPositions.get(id);
        if (positions != null && !positions.isEmpty()) {
            Point2D p = positions.pop();
            go.moveTo(p);
        }
    }
}
