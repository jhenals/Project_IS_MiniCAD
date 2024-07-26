package MiniCAD;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandIF;
import MiniCAD.interpreter.commands.UndoableCommand;
import MiniCAD.interpreter.lexerparser.CommandParser;

import java.io.IOException;
import java.util.Scanner;

public class MiniCadCLI {
    public static void main(String[] args) {
        Context context = new Context();
        SistemaMiniCAD sistemaMiniCAD = new SistemaMiniCAD();
        CommandParser commandParser = new CommandParser();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Benvenuto in MiniCAD CLI. Digita i comandi. Digita 'exit' per uscire.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                if (input.equalsIgnoreCase("undo")) {
                    sistemaMiniCAD.undo();
                } else if (input.equalsIgnoreCase("redo")) {
                    sistemaMiniCAD.redo();
                } else{
                    CommandIF command = commandParser.parseCommand(input);
                    if (command instanceof UndoableCommand)
                        sistemaMiniCAD.esegueComando((UndoableCommand) command);
                    else
                        command.interpreta(context);
                }
            } catch (ParseException | IOException e) {
                System.err.println("Errore durante l'interpretazione del comando: " + e.getMessage());
            } catch (ClassCastException e) {
                System.err.println("Il comando non è undoable: " + e.getMessage());
            }

            //System.out.println(objectManager);
        }

        scanner.close();
    }
}