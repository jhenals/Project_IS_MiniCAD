package MiniCAD.interpreter.lexerparser;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.commands.Command;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class MiniCADInterpreter {
    public static void main (String[] args) throws IOException {
       String newImgInput = "new img (\"./pippo.png\") (6.1,4.6)";
       String newCircInput = "new circle (5.0) (3.1,4.5)";
       String newRectInput = "new rectangle (3.1,4.5) (0.0,0.0)";
       String mvInputInput = "mv id1 (5.9,8.2)";

       /*
        CommandLexer cLexer = new CommandLexer( new StringReader(commandInput) );
        List<Token> tokens = cLexer.tokenizzare();
        for (Token t: tokens ){
            System.out.println(t.toString());
        }
         */




        try{

            CommandParser parser = new CommandParser();
            Command command = parser.parseCommand(newImgInput);
            command.interpreta(); // Esegui il comando


        }catch (IOException | ParseException e){
            e.printStackTrace();
        }






    }
}
