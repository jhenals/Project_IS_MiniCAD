package MiniCAD.interpreter.lexerparser;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.commands.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandParser {
    CommandLexer cLexer;
    Iterator<Token> tokens;
    Token tokenCorrente;

    public CommandParser( String command ) throws IOException {
        cLexer = new CommandLexer(new StringReader(command));
        try {
            parseCommand();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        avanza();
    }

    private void parseCommand() throws ParseException {
        if( tokenCorrente == null ){
            throw new IllegalArgumentException("Unexpected end of input");
        }
        switch (tokenCorrente.getTipo()){
            case NEW:
                parseCreate();
                break;
            case DEL :
                parseRemove();
                break;
            case MV:
            case MVOFF:
                parseMove();
                break;
            case SCALE:
                parseScale();
                break;
            case LS:
                parseList();
                break;
            case GRP:
                parseGroup();
                break;
            case UNGRP:
                parseUngroup();
                break;
            case AREA:
                parseArea();
                break;
            case PERIMETER:
                parsePerimeter();
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + tokenCorrente.getValore());
        }
    } //parseCommand


    private <T> Command parseCreate() throws ParseException {
        expect(TokenType.NEW);
        TokenType tipo = tokenCorrente.getTipo();
        T param = null;
        expect(TokenType.TONDA_APERTA);
        switch (tipo) {
            case CIRCLE:
                param = (T) tokenCorrente; //Pos_float
                break;
            case RECTANGLE:
                Token width = tokenCorrente;
                expect(TokenType.VIRGOLA);
                Token height = tokenCorrente;
                Posizione p = new Posizione(width, height);
                param = (T) p;
                break;
            case IMG:
                param = (T) tokenCorrente;
                break;
        }
        expect(TokenType.TONDA_CHIUSA);
        TypeConstraint typeConstraint = new TypeConstraint<>(tipo,param );
        expect(TokenType.TONDA_APERTA);
        Token x = tokenCorrente;
        expect(TokenType.VIRGOLA);
        Token y = tokenCorrente;
        Posizione pos = new Posizione(x, y);

        switch (tipo){
            case CIRCLE -> {
                return new CreateCircleCommand(typeConstraint, pos);
            }
            case RECTANGLE -> {
                return new CreateRectangleCommand(typeConstraint, pos);
            }
            case IMG -> {
                return new CreateImageCommand(typeConstraint, pos);
            }
            default -> throw new RuntimeException("Errore nella creazione dell'oggetto");
        }

    }

    private Command parseRemove() throws ParseException {
        expect(TokenType.DEL);
        if( tokenCorrente.getTipo() != TokenType.OBJ_ID ){
            throw new ParseException("Token non è un id.");
        }
        String id = tokenCorrente.getValore().toString();
        return new RemoveCommand(id);
    }


    private Command parseMove() throws ParseException {
        Token commandType = tokenCorrente;
        avanza();
        Token objId = null;
        if( tokenCorrente.getTipo() == TokenType.OBJ_ID){
            objId = tokenCorrente;
        }
        expect(TokenType.TONDA_APERTA);
        Token x = tokenCorrente;
        expect(TokenType.VIRGOLA);
        Token y = tokenCorrente;
        expect(TokenType.TONDA_CHIUSA);
        Posizione pos = new Posizione(x,y);
        if( commandType.getTipo() == TokenType.MV ){
            return new MoveCommand(objId, pos);
        }else if( commandType.getTipo() == TokenType.MVOFF ){
            return new MoveOffCommand(objId, pos);
        } else{
            throw new ParseException("");
        }
    }


    private Command parseScale() throws ParseException {
        expect(TokenType.SCALE);
        Token objectId = null;
        if( tokenCorrente.getTipo() == TokenType.OBJ_ID)
            objectId = tokenCorrente;
        avanza();
        Token dim= null;
        if( tokenCorrente.getTipo() == TokenType.POS_FLOAT)
            dim = tokenCorrente;
        return new ScaleCommand(objectId, dim);
    }

    private Command parseList() throws ParseException {
        expect(TokenType.LS);
        Token param = tokenCorrente;
        return  new ListCommand(param);
    }

    private Command parseGroup() throws ParseException {
        expect(TokenType.GRP);
        List<Token> objIds = new ArrayList<>();
        while( tokenCorrente.getTipo() == TokenType.OBJ_ID ){
            objIds.add(tokenCorrente);
        }
        ListId listId = new ListId(objIds);
        return new GroupCommand(listId);

    }

    private Command parseUngroup() throws ParseException {
        expect(TokenType.UNGRP);
        Token groupId = tokenCorrente;
        if( groupId.getTipo() == TokenType.OBJ_ID ){
            return new UngroupCommand(groupId);
        }else{
            throw new ParseException("Si aspetta un tipo OBJ_ID ma trova un tipo + " + tokenCorrente.getTipo() );
        }

    }

    private Command parseArea() throws ParseException {
        expect(TokenType.AREA);
        Token param = tokenCorrente;
        return new AreaCommand(param);
    }

    private Command parsePerimeter() throws ParseException {
        expect(TokenType.PERIMETER);
        Token param = tokenCorrente;
        return new PerimeterCommand(param);
    }

    private void expect(TokenType tipo) throws ParseException {
        if( tokenCorrente == null || tokenCorrente.getTipo() != tipo ){
            throw new ParseException("Expected token of type " + tipo + " but found " + tokenCorrente);
        }
        avanza();
    }

    private void avanza() {
        if( tokens.hasNext()){
            tokenCorrente = tokens.next();
        }else{
            tokenCorrente = null;
        }
    }



}
