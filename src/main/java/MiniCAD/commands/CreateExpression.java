package MiniCAD.commands;

import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;
import ObserverCommandFlyweight.is.shapes.model.RectangleObject;

import java.awt.geom.Point2D;

public class CreateExpression implements  Expression{
    private String type, objectName;
    private double x, y, dimension1, dimension2;

    public CreateExpression( String type, String objectName, double x, double y, double dimension1, double dimension2){
        this.type = type;
        this.objectName = objectName;
        this.x = x;
        this.y = y;
        this.dimension1 = dimension1;
        this.dimension2 = dimension2;
    }

    @Override
    public void interpret(Context context) {
        GraphicObject obj = null;
        Point2D position = new Point2D.Double(x, y);

        switch (( type.toLowerCase())){
            case "rectangle":
                obj = new RectangleObject(position, dimension1, dimension2);
                break;
            case "circle":
                obj = new CircleObject(position, dimension1);
                break;
            /*
            case "image":
                obj = new ImageObject(imageIcon, position);

             */
        }
        if ( obj != null ){
            context.addObject(objectName, obj);
        }else{
            System.out.println("Invalid object type:" + type);
        }

    }
}
