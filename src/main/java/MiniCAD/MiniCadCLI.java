package MiniCAD;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.Command;
import MiniCAD.interpreter.commands.CommandExprIF;
import MiniCAD.interpreter.commands.UndoableCmdExprIF;
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
                    Command command = (Command) commandParser.parseCommand(input);
                    if (command instanceof UndoableCmdExprIF)
                        sistemaMiniCAD.esegueComando( command, context);
                    else
                       command.interpreta(context);
                }
            } catch (ParseException | IOException e) {
                System.err.println("Errore durante l'interpretazione del comando: " + e.getMessage());
            } catch (ClassCastException e) {
                System.err.println("Il comando non è undoable: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
