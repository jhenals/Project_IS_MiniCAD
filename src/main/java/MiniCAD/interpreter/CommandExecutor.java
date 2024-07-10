package MiniCAD.interpreter;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.commands.Command;
import MiniCAD.interpreter.lexerparser.CommandParser;

import java.io.IOException;

public class CommandExecutor {
    public static void main(String[] args){
        ObjectManager objMngr = ObjectManager.getInstance();
        //String input = "ls all";
        String createCommandinput = "new circle (5.0) (3.1,4.5)";  // Example input
        String moveCommandInpt = "mvoff id0 (5.9,8.2)";
        try{
            CommandParser parser = new CommandParser();
            Command command = parser.parseCommand(createCommandinput);
            command.interpreta();

            Command moveCommand = parser.parseCommand(moveCommandInpt);
            moveCommand.interpreta();

            System.out.println(objMngr.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
