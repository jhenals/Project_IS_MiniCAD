package MiniCAD.interpreter;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.commands.Command;
import MiniCAD.interpreter.lexerparser.CommandParser;

import java.io.IOException;

public class CommandExecutor {
    public static void main(String[] args){
        ObjectManager objMngr = ObjectManager.getInstance();

        String createCommandInput1 = "new circle (5.0) (3.1,4.5)";
        String createCommandInput2 = "new circle (3.0) (6.1,9.5)";
        String createCommandInput3 = "create img (“./pippo.png”) (6.1,4.6)";

        String delCommandInput ="del id1";

        String moveCommandInput1 = "mv id0 (5.9,8.2)";
        String moveCommandInput2 = "mvoff id1 (5.9,8.2)";

        String scaleCommandInput = "scale id1 2.0";

        String listCommandInput1 = "ls id1";
        String listCommandInput2 = "ls Circle";
        String listCommandInput3 = "ls all";
        String listCommandInput4 = "ls groups";

        String grpCommandInput = "grp id1, id2, id3";

        String ungrpCommandInput = "ungrp gid0";

        String areaCommandInput = "area id1";
        String perimeterCommandInput = "perimeter rectangle";
        String areaCommandInput2 = "area all";


        try{
            CommandParser parser = new CommandParser();

            Command createCommand1 = parser.parseCommand(createCommandInput1);
            createCommand1.interpreta();

            Command createCommand2 = parser.parseCommand(createCommandInput2);
            createCommand2.interpreta();

            /*
            Command listCommand1 = parser.parseCommand(listCommandInput1);
            listCommand1.interpreta();

            Command listCommand2 = parser.parseCommand(listCommandInput2);
            listCommand2.interpreta();

             */

            Command scaleCommand = parser.parseCommand(scaleCommandInput);
            scaleCommand.interpreta();


            /*
            Command moveCommand = parser.parseCommand(moveCommandInpt);
            moveCommand.interpreta();

            System.out.println(objMngr.toString());

             */

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
