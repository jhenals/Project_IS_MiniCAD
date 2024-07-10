package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.utils.Token;

public class AreaCommand implements  Command {
    private Token param;

    public AreaCommand(Token param) {
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
        return "AreaCommand{" +
                "param=" + param +
                '}';
    }
}
