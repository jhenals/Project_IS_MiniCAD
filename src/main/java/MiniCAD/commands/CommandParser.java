package MiniCAD.commands;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class CommandParser {
    public static Expression parse (String command) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer((new StringReader(command)));
        tokenizer.whitespaceChars(' ', ' ');
        tokenizer.whitespaceChars('\t', '\t');
        tokenizer.whitespaceChars('\n', '\n');
        tokenizer.whitespaceChars('\r', '\r');
        tokenizer.whitespaceChars('_', '_');
        
        tokenizer.nextToken();
        String commandType = tokenizer.sval;
        
        switch ( commandType.toLowerCase()){
            case "create":
                return parseCreateCommand(tokenizer);
            default:
                throw new IllegalArgumentException("Unknown command: " + commandType);
        }
    }

    private static Expression parseCreateCommand(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        String type = tokenizer.sval;

        tokenizer.nextToken();
        String objectName = tokenizer.sval;

        tokenizer.nextToken();
        double x = parseNumber(tokenizer);

        tokenizer.nextToken();
        double y = parseNumber(tokenizer);

        tokenizer.nextToken();
        double dimension1 = parseNumber(tokenizer);

        tokenizer.nextToken();
        double dimension2 = tokenizer.ttype != StreamTokenizer.TT_EOF ? parseNumber(tokenizer) : 0;


        return new CreateExpression(type, objectName, x, y, dimension1, dimension2);

    }

    private static double parseNumber(StreamTokenizer tokenizer) throws  IOException {
        if ( tokenizer.ttype == StreamTokenizer.TT_NUMBER ){
            return tokenizer.nval;
        }else{
            throw new IllegalArgumentException("Previsto un numero ma trovato: " + tokenizer.sval);
        }
    }
}
