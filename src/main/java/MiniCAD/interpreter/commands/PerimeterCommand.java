package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;

public class PerimeterCommand implements  Command{
    private Token param;

    public PerimeterCommand(Token param) {
        this.param = param;
    }

    public Token getParam() {
        return param;
    }

    public void setParam(Token param) {
        this.param = param;
    }

    @Override
    public void interpreta() {
        //TODO
    }

    @Override
    public String toString() {
        return "PerimeterCommand{" +
                "parametro=" + param +
                '}';
    }

    @Override
    public void undo() {

    }


}
