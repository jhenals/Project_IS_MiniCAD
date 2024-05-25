package MiniCAD.interpreter.lexerparser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class CommandLexer {

    private final StreamTokenizer tokenizer;
   private Token token;

    public CommandLexer(StringReader input) {
        tokenizer = new StreamTokenizer(input);
        configuraTokenizer( tokenizer );
    }

    public List<Token> tokenizzare() throws IOException {
        List<Token> tokens = new ArrayList<>();
        int tipoToken;
        while( (tipoToken = tokenizer.nextToken()) != StreamTokenizer.TT_EOF ) {
            //Token token;
            switch (tipoToken) {
                case StreamTokenizer.TT_WORD -> {
                    //Parole riservate
                    if (tokenizer.sval.startsWith("id")) {
                        token = new Token(TokenType.OBJ_ID, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("new")) {
                        token = new Token(TokenType.NEW, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("create")) {
                        token = new Token(TokenType.NEW, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("del")) {
                        token = new Token(TokenType.DEL, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("mv")) {
                        token = new Token(TokenType.MV, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("mvoff")) {
                        token = new Token(TokenType.MVOFF, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("scale")) {
                        token = new Token(TokenType.SCALE, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("ls")) {
                        token = new Token(TokenType.LS, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("all")) {
                        token = new Token(TokenType.ALL, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("groups")) {
                        token = new Token(TokenType.GROUPS, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("grp")) {
                        token = new Token(TokenType.GRP, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("ungrp")) {
                        token = new Token(TokenType.UNGRP, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("area")) {
                        token = new Token(TokenType.AREA, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("perimeter")) {
                        token = new Token(TokenType.PERIMETER, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("img")) {
                        token = new Token(TokenType.IMG, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("circle")) {
                        token = new Token(TokenType.CIRCLE, tokenizer.sval);
                    } else if (tokenizer.sval.equalsIgnoreCase("rectangle")) {
                        token = new Token(TokenType.RECTANGLE, tokenizer.sval);
                    }
                }
                case StreamTokenizer.TT_NUMBER -> token = new Token(TokenType.POS_FLOAT, tokenizer.nval);
                case '(' -> token = new Token(TokenType.TONDA_APERTA, "(");
                case ')' -> token = new Token(TokenType.TONDA_CHIUSA, ")");
                case ',' -> token = new Token(TokenType.VIRGOLA, ",");
                default -> {
                    if (tokenizer.sval.matches("./[a-zA-Z0-9]+.[a-zA-Z0-9]+")) {
                        token = new Token(TokenType.PATH, tokenizer.sval);
                    } else {
                        token = new Token(TokenType.CHAR_INVALIDO, tokenizer.toString());
                    }
                }
            }
            tokens.add(token);
        }
        return tokens;
    }


    private void configuraTokenizer(StreamTokenizer tokenizer) {
        tokenizer.whitespaceChars(' ', ' ');
        tokenizer.whitespaceChars('\t', '\t');
        tokenizer.whitespaceChars('\n', '\n');
        tokenizer.whitespaceChars('\r', '\r');
        tokenizer.whitespaceChars('_', '_');
        tokenizer.eolIsSignificant(false);
        tokenizer.ordinaryChar('(');
        tokenizer.ordinaryChar(')');
        tokenizer.wordChars('.' , '.');
        tokenizer.wordChars('/', '/');
        tokenizer.wordChars('_', '_'); // Allow underscore in words
        tokenizer.quoteChar('"'); // Treat quotes as special characters
    }
}
