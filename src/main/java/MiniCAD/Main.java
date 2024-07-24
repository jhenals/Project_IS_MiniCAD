package MiniCAD;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.commands.UndoableCommand;
import MiniCAD.interpreter.lexerparser.CommandParser;

import java.io.IOException;

public class Main {
    public static void main(String[]args){
        ObjectManager objectManager = ObjectManager.getInstance();
        SistemaMiniCAD sistemaMiniCAD = new SistemaMiniCAD();

        String createCommandInput1 = "new circle (5.0) (3.1,4.5)";
        String createCommandInput2 = "new rectangle (4.0,5.0) (3.1,4.5)";
        String createCommandInput3 = "new img (\"./pippo.png\") (6.1,4.6)";
        try{
            CommandParser commandParser = new CommandParser();
            UndoableCommand createCommand1 = (UndoableCommand) commandParser.parseCommand(createCommandInput1);
            UndoableCommand createCommand2 = (UndoableCommand) commandParser.parseCommand(createCommandInput2);
            UndoableCommand createCommand3 = (UndoableCommand) commandParser.parseCommand(createCommandInput3);

            sistemaMiniCAD.esegueComando(createCommand1);
            sistemaMiniCAD.esegueComando(createCommand2);
            sistemaMiniCAD.esegueComando(createCommand3);
        } catch (
                ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(objectManager);
        System.out.println("-----------------------------------------------------");
        sistemaMiniCAD.undo();
        System.out.println(objectManager);

    }
}
