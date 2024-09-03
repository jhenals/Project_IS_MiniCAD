package MiniCAD.miniinterpreter.model;

import ObserverCommandFlyweight.is.shapes.model.AbstractGraphicObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObjectListener;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupObject extends AbstractGraphicObject {
    private final String groupId ;
    private Map<String, GraphicObject> objects;
    private Point2D position;

    public GroupObject(String gid){
        groupId= gid;
        objects = new HashMap<>();
    }

    public String getGroupId(){ return groupId; }

    public List<String> getObjectIds(){
        return objects.keySet().stream().toList();
    }

    public void setObjects(Map<String,GraphicObject> objs){
        objects = objs;
    }

    @Override
    public void addGraphicObjectListener(GraphicObjectListener l) {
        /*
        for (GraphicObject obj : objects.values()) {
            obj.addGraphicObjectListener(l);
        }
         */
    }

    @Override
    public void removeGraphicObjectListener(GraphicObjectListener l) {
        /*
        for (GraphicObject obj : objects.values()) {
            obj.removeGraphicObjectListener(l);
        }
         */
    }

    @Override
    public void moveTo(Point2D p) {
        Point2D currentPos = getPosition();
        double deltaX = p.getX() - currentPos.getX();
        double deltaY = p.getY() - currentPos.getY();
        for( GraphicObject obj : objects.values() ){
            Point2D objPos = obj.getPosition();
            obj.moveTo(objPos.getX() + deltaX , objPos.getY() + deltaY );
        }
    }

    @Override
    public void moveTo(double x, double y) {
        moveTo(new Point2D.Double(x,y));
    }

    @Override
    public Point2D getPosition() {
        if( objects.isEmpty() ){
            return new Point2D.Double(0,0);
        }
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        for( GraphicObject obj : objects.values() ){
            Point2D pos = obj.getPosition();
            if( pos.getX() < minX ){
                minX = pos.getX();
            }
            if( pos.getY() < minY ){
                minY = pos.getY();
            }
        }
        return new Point2D.Double(minX, minY);
    }

    @Override
    public Dimension2D getDimension() {
        if (objects.isEmpty()) {
            return new Dimension2D() {
                private double width = 0;
                private double height = 0;

                @Override
                public double getWidth() {
                    return width;
                }

                @Override
                public double getHeight() {
                    return height;
                }

                @Override
                public void setSize(double width, double height) {
                    this.width = width;
                    this.height = height;
                }
            };
        }
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        for (GraphicObject obj : objects.values()) {
            Point2D pos = obj.getPosition();
            Dimension2D dim = obj.getDimension();
            if (pos.getX() + dim.getWidth() > maxX) {
                maxX = pos.getX() + dim.getWidth();
            }
            if (pos.getY() + dim.getHeight() > maxY) {
                maxY = pos.getY() + dim.getHeight();
            }
        }
        final double width = maxX - getPosition().getX();
        final double height = maxY - getPosition().getY();

        return new Dimension2D() {
            @Override
            public double getWidth() {
                return width;
            }

            @Override
            public double getHeight() {
                return height;
            }

            @Override
            public void setSize(double width, double height) {
                // Dimensions are calculated, not set manually
            }
        };
    }

    @Override
    public void scale(double factor) {
        for (GraphicObject obj : objects.values()) {
            obj.scale(factor);
        }
    }

    @Override
    public boolean contains(Point2D p) {
        for (GraphicObject obj : objects.values()) {
            if (obj.contains(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return "Group";
    }

    @Override
    public GroupObject clone() {
        GroupObject cloned = (GroupObject) super.clone();
        cloned.position = (Point2D) position.clone();
        return cloned;
    }
}
