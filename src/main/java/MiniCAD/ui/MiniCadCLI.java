package MiniCAD.ui;

import MiniCAD.exceptions.ParseException;
import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.commandsExpr.CommandExprIF;
import MiniCAD.shapes.interpreter.commandsExpr.UndoableCmdExprIF;
import MiniCAD.shapes.interpreter.lexerparser.CommandParser;
import ObserverCommandFlyweight.is.shapes.view.GraphicObjectPanel;

import java.io.IOException;
import java.util.Scanner;

public class MiniCadCLI {
    public static void main(String[] args) {
        final GraphicObjectPanel gpanel = new GraphicObjectPanel();
        Context context = new Context(gpanel);
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
                } else{
                    CommandExprIF command = commandParser.parseCommand(input);
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
