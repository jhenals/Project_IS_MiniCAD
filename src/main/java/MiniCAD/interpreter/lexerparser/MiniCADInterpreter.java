package MiniCAD.interpreter.lexerparser;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandExprIF;

import java.io.IOException;

public class MiniCADInterpreter {
    public static void main (String[] args) throws IOException {
        Context context = new Context();
       String newImgInput = "new img (\"./pippo.png\") (6.1,4.6)";
       String newCircInput = "new circle (5.0) (3.1,4.5)";
       String newRectInput = "new rectangle (3.1,4.5) (0.0,0.0)";
       String mvInputInput = "mvoff id0 (5.9,8.2)";

       /*
        CommandLexer cLexer = new CommandLexer( new StringReader(commandInput) );
        List<Token> tokens = cLexer.tokenizzare();
        for (Token t: tokens ){
            System.out.println(t.toString());
        }
         */


        try{
            CommandParser parser = new CommandParser();
            CommandExprIF command = parser.parseCommand(newImgInput);
            CommandExprIF command2 = parser.parseCommand(newCircInput);
            CommandExprIF command3 = parser.parseCommand(newRectInput);
            CommandExprIF command4 = parser.parseCommand(mvInputInput);
            command.interpreta(context); // Esegui il comando
            command2.interpreta(context);
            command3.interpreta(context);
            command4.interpreta(context);



        }catch (IOException | ParseException e){
            e.printStackTrace();
        }






    }
}
