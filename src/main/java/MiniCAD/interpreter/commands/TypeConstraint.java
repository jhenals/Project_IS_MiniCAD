package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.TokenType;

public class TypeConstraint<T> implements Command{
    private TokenType tipo;
    private T parameter;

    public TypeConstraint(TokenType tipo, T param ){
        this.tipo = tipo;
        this.parameter = param;
    }

    public TokenType getTipo() {
        return tipo;
    }

    public void setTipo(TokenType tipo) {
        this.tipo = tipo;
    }

    public T getParameter() {
        return parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }

    @Override
    public void interpreta() {
        switch (tipo) {
            case CIRCLE:
                System.out.println("Circle with radius: " + parameter.toString());
                break;
            case RECTANGLE:
                System.out.println("Rectangle with width and height: " + parameter.toString());
                break;
            case IMG:
                System.out.println("Image with path: \"./" + parameter.toString()+ "\".");
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + tipo);
        }
    }

    @Override
    public String toString(){
        String res="";
        switch (tipo) {
            case CIRCLE:
                res="Circle with radius: " + parameter.toString();
                break;
            case RECTANGLE:
                res= "Rectangle.";
                break;
            case IMG:
                res="Image with path: \"./" + parameter.toString()+ "\".";
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + tipo);
        }
        return res;
    }

}
