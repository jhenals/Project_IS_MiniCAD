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
        String createCommandInput3 = "new img (“./pippo.png”) (6.1,4.6)";

        String delCommandInput ="del id1";
        String delCommandInput2 = "del gid4";

        String moveCommandInput1 = "mv id0 (5.9,8.2)";
        String moveCommandInput2 = "mvoff id1 (5.9,8.2)";

        String scaleCommandInput1 = "scale id1 2.0";
        String scaleCommandInput2 = "scale gid4 2.0";

        String listCommandInput1 = "ls id1";
        String listCommandInput2 = "ls Circle";
        String listCommandInput3 = "ls all";
        String listCommandInput4 = "ls groups";

        String grpCommandInput = "grp id0, id1";
        String grpCommandInput2 = "grp gid3, id2";

        String ungrpCommandInput = "ungrp gid4";

        String areaCommandInput1 = "area id1";
        String areaCommandInput2 = "area all";
        String areaCommandInput3 = "area gid4";

        String perimeterCommandInput1 = "perimeter circle";

        try{
            CommandParser parser = new CommandParser();

            Command createCommand1 = parser.parseCommand(createCommandInput1);
            createCommand1.interpreta();

            Command createCommand2 = parser.parseCommand(createCommandInput2);
            createCommand2.interpreta();

            Command createCommand3 = parser.parseCommand(createCommandInput3);
            createCommand3.interpreta();

            Command grpCommand1 = parser.parseCommand(grpCommandInput);
            grpCommand1.interpreta();

            Command grpCommand2 = parser.parseCommand(grpCommandInput2);
            grpCommand2.interpreta();

            /*


            Command listCommand2 = parser.parseCommand(listCommandInput2);
            listCommand2.interpreta();

             */


            Command areaCommand1 = parser.parseCommand(areaCommandInput1);
            areaCommand1.interpreta();

            Command areaCommand2 = parser.parseCommand(areaCommandInput2);
            areaCommand2.interpreta();

            Command areaCommand3 = parser.parseCommand(areaCommandInput3);
            areaCommand3.interpreta();

            Command perimCommand1 = parser.parseCommand(perimeterCommandInput1);
            perimCommand1.interpreta();

            /*
            Command ungrpCommand = parser.parseCommand(ungrpCommandInput);
            ungrpCommand.interpreta();

            Command delCommand = parser.parseCommand(delCommandInput);
            delCommand.interpreta();

             */
            Command moveCommand = parser.parseCommand(moveCommandInput1);
            moveCommand.interpreta();

            Command moveCommand2 = parser.parseCommand(moveCommandInput2);
            moveCommand2.interpreta();

            Command scaleCommand = parser.parseCommand(scaleCommandInput1);
            scaleCommand.interpreta();

            Command listCommand1 = parser.parseCommand(listCommandInput2);
            listCommand1.interpreta();


            System.out.println(objMngr.toString());




        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
