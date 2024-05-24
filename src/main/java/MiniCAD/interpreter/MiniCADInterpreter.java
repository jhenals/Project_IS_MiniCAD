package MiniCAD.interpreter;

import MiniCAD.interpreter.lexerparser.CommandLexer;
import MiniCAD.interpreter.lexerparser.Token;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class MiniCADInterpreter {
    public static void main (String[] args) throws IOException {
        String commandInput = "new img (\"./pippo.png\") (6.1,4.6)";
        //String commandInput = "create circle (5.0) (3.1,4.5)";
        CommandLexer cLexer = new CommandLexer( new StringReader(commandInput) );
        List<Token> tokens = cLexer.tokenizzare();
        for (Token t: tokens ){
            System.out.println(t.toString());
        }
    }
}
