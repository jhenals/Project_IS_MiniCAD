package MiniCAD.commands;

import java.io.IOException;
import java.net.ConnectException;

public class MiniCADInterpreter {
    public static void main ( String[] args){
        Context context = new Context();

        String[]commands = {
                "create rectangle (5.0) (3.1, 4.5)"
        };

        for ( String c: commands){
            try{
                Expression expr = CommandParser.parse(c);
                expr.interpret(context);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
