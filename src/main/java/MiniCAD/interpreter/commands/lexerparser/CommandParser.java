package MiniCAD.interpreter.commands.lexerparser;

import MiniCAD.interpreter.commands.Command;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

public class CommandParser {
    CommandLexer cLexer;
    Iterator<Token> tokenIterator;
    Token tokenCorr;

    public Command parse(String command) throws  IOException{
        cLexer = new CommandLexer(new StringReader(command));
        List<Token> tokens = cLexer.tokenizzare();
        tokenIterator = tokens.iterator();
        avanza();
        return parseCommand();
    }


    private void avanza() {
        if( tokenIterator.hasNext()){
            tokenCorr = tokenIterator.next();
        }else{
            tokenCorr = null;
        }
    }

    private Command parseCommand(){
        if( tokenCorr == null ){
            throw new IllegalArgumentException("Unexpected end of input");
        }
        switch (tokenCorr.getValore().toString()){
            case "create":
                return parseCreate();
            case "del" :
                return parseRemove();
            case "mv":
            case "mvoff":
                return parseMove();
            case "scale":
                return parseScale();
            case "ls":
                return parseList();
            case "grp":
                return parseGroup();
            case "ungrp":
                return parseUngroup();
            case "area":
                return parseArea();
            case "perimeter":
                return parsePerimeter();
            default:
                throw new IllegalArgumentException("Unknown command: " + tokenCorr.getValore());
        }
    } //parseCommand


    private Command parseCreate() {
        /*
        String tipoOggetto = tokenCorr.getValore();
        avanza();

        Double x = Double.parseDouble(tokenCorr.getValore());
        avanza();
        Double y = Double.parseDouble(tokenCorr.getValore());
        Point2D pos = new Point2D.Double(x,y);
      */
        return null;
    }



    private Command parseMove() {
        return null;
    }

    private Command parseRemove() {
        return null;
    }

    private Command parseScale() {
        return null;
    }

    private Command parseList(){
        return null;

    }

    private Command parseGroup(){
        return null;

    }

    private Command parseUngroup(){
        return null;

    }

    private Command parseArea(){
        return null;

    }

    private Command parsePerimeter(){
        return null;

    }


}
