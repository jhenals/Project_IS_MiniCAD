package MiniCAD.interpreter.lexerparser;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.commands.*;
import MiniCAD.interpreter.utils.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandParser {
    CommandLexer cLexer;
    Iterator<Token> tokens;
    Token tokenCorrente;

    public Command parseCommand(String command ) throws ParseException, IOException {
        cLexer = new CommandLexer(new StringReader(command));
        List<Token> listaToken = cLexer.tokenizzare();
        this.tokens = listaToken.iterator();
        avanza();

        if( tokenCorrente == null ){
            throw new IllegalArgumentException("Fine dell'input inattesa");
        }
        TokenType commando = tokenCorrente.getTipo();
        return switch (commando) {
            case NEW -> parseCreate();
            case DEL -> parseRemove();
            case MV, MVOFF -> parseMove();
            case SCALE -> parseScale();
            case LS -> parseList();
            case GRP -> parseGroup();
            case UNGRP -> parseUngroup();
            case AREA -> parseArea();
            case PERIMETER -> parsePerimeter();
            default -> throw new IllegalArgumentException("Tipo del comando sconosciuto: " + tokenCorrente.getValore());
        };

    } //parseCommand


    private Command parseCreate() throws ParseException {
        expect(TokenType.NEW);
        TokenType tipoOggetto = tokenCorrente.getTipo();
        avanza();

        //Token Constraint: <typeconstr>::= circle (<posfloat>) | rectangle <pos> | img (<path>)
        expect(TokenType.TONDA_APERTA);
        TypeConstructor typeConstructor = null;
        switch (tipoOggetto) {
            case CIRCLE ->{
                float raggio = Float.parseFloat(tokenCorrente.getValore().toString()); //Pos_float per raggio
                typeConstructor = new TypeConstructor.CircleConstructor(raggio);
            }
            case RECTANGLE -> {
                float width = Float.parseFloat(tokenCorrente.getValore().toString());
                avanza();
                expect(TokenType.VIRGOLA);
                float height = Float.parseFloat(tokenCorrente.getValore().toString());;
                Posizione p = new Posizione(width, height); // (base, altezza)
                typeConstructor = new TypeConstructor.RectangleConstuctor(p);
            }
            case IMG ->{
                String path = (String) tokenCorrente.getValore();
                typeConstructor = new TypeConstructor.ImageConstructor(path);
            }
            default -> throw  new ParseException("Tipo sconosciuto: "+ tipoOggetto.toString());
        }
        avanza();
        expect(TokenType.TONDA_CHIUSA);
        Posizione p = parsePosition();
        return new CreateCommand(typeConstructor, p);
    }

    private Command parseRemove() throws ParseException {
        expect(TokenType.DEL);
        if( tokenCorrente.getTipo() != TokenType.OBJ_ID ){
            throw new ParseException("Token non è un id.");
        }
        String objId = tokenCorrente.getValore().toString(); //Obj_id
        return new RemoveCommand(objId);
    }


    private Command parseMove() throws ParseException {
        boolean offset = tokenCorrente.getTipo().equals(TokenType.MVOFF);
        avanza();
        Token objId = tokenCorrente;
        avanza();
        Posizione pos= parsePosition();
        return new MoveCommand(objId, pos, offset);
    }


    private Command parseScale() throws ParseException {
        expect(TokenType.SCALE);
        Token objectId = tokenCorrente;
        avanza();
        Token dim= tokenCorrente;
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

    private Posizione parsePosition() throws ParseException {
        //Posizione : <pos>::=( <posfloat> , <posfloat> )
        expect(TokenType.TONDA_APERTA);
        String x = tokenCorrente.getValore().toString();
        avanza();
        expect(TokenType.VIRGOLA);
        String y = tokenCorrente.getValore().toString();
        avanza();
        expect(TokenType.TONDA_CHIUSA);
        Posizione pos = new Posizione(x,y);
        return pos;
    }

    private boolean expect(TokenType tipo) throws ParseException {
        if( tokenCorrente == null || tokenCorrente.getTipo() != tipo ){
            throw new ParseException("Si aspetta un Token di tipo " + tipo + ", ma trovato " + tokenCorrente);
        }
        avanza();
        return true;
    }

    private void avanza() {
        if( tokens.hasNext()){
            tokenCorrente = tokens.next();
        }else{
            tokenCorrente = null;
        }
    }



}
