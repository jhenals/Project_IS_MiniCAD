package MiniCAD.interpreter.lexerparser;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.commands.Command;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class MiniCADInterpreter {
    public static void main (String[] args) throws IOException {
       // String commandInput = "new img (\"./pippo.png\") (6.1,4.6)";
       // String commandInput2 = "new circle (5.0) (3.1,4.5)";
        String commandInput3 = "new rectangle (3.1,4.5) (0.0,0.0)";

        CommandLexer cLexer = new CommandLexer( new StringReader(commandInput3) );
        List<Token> tokens = cLexer.tokenizzare();
        for (Token t: tokens ){
            System.out.println(t.toString());
        }



        try{

            /*
            CommandParser parser = new CommandParser(commandInput);
            Command command = parser.parseCommand();
            command.interpreta(); // Esegui il comando

            CommandParser parser2 = new CommandParser(commandInput2);
            Command command2 = parser2.parseCommand();
            command2.interpreta(); // Esegui il comando



             */

            CommandParser parser3 = new CommandParser(commandInput3);
            Command command3= parser3.parseCommand();
            command3.interpreta();

        }catch (IOException | ParseException e){
            e.printStackTrace();
        }






    }
}
