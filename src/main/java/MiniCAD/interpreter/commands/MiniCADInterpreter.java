package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.commands.lexerparser.CommandLexer;
import MiniCAD.interpreter.commands.lexerparser.Token;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

public class MiniCADInterpreter {
    public static void main (String[] args) throws IOException {
        String commandInput = "new img ('./pippo.png') (6.1,4.6)";
        CommandLexer cLexer = new CommandLexer( new StringReader(commandInput) );
        List<Token> tokens = cLexer.tokenizzare();
        for (Token t: tokens ){
            System.out.println(t.toString());
        }
    }
}
