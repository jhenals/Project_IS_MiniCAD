package MiniCAD.mvc.specificCmds.lexerparser;

import MiniCAD.exceptions.ParseException;
import MiniCAD.mvc.specificCmds.commandsExpr.*;
import MiniCAD.mvc.specificCmds.utilExpr.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandParser {
    private Iterator<Token> tokens;
    private Token tokenCorrente;


    public CommandExprIF parseCommand(String cmdInput) throws ParseException, IOException {
        CommandExprIF cmd;
        CommandLexer cLexer = new CommandLexer(new StringReader(cmdInput));
        List<Token> listaToken = cLexer.tokenizzare();
        this.tokens = listaToken.iterator();
        avanza();

        if( tokenCorrente == null ){
            throw new IllegalArgumentException("Fine dell'input inattesa");
        }
        TokenType commando = tokenCorrente.getTipo();
        switch (commando) {
            case NEW ->{
                CreateCommand createCmd = parseCreate();
                cmd = new Command(createCmd);
            }
            case DEL -> {
                RemoveCommand removeCmd = parseRemove();
                cmd = new Command(removeCmd);
            }
            case MV, MVOFF ->{
                MoveCommand moveCmd = parseMove();
                cmd = new Command(moveCmd);
            }
            case SCALE ->{
                ScaleCommand scaleCmd = parseScale();
                cmd = new Command( scaleCmd);
            }
            case LS ->{
               ListCommand listCmd = parseList();
               cmd = new Command(listCmd);
            }
            case GRP ->{
                GroupCommand groupCmd = parseGroup();
                cmd = new Command(groupCmd);
            }
            case UNGRP ->{
                UngroupCommand ungroupCmd = parseUngroup();
                cmd = new Command(ungroupCmd);
            }
            case AREA ->{
                AreaCommand areaCmd = parseArea();
                cmd = new Command(areaCmd);
            }
            case PERIMETER ->{
                PerimeterCommand perimeterCmd = parsePerimeter();
                cmd = new Command(perimeterCmd);
            }
            default -> throw new IllegalArgumentException("Tipo del comando sconosciuto: " + tokenCorrente.getValore());
        }
        return cmd;
    } //parseCommand


    private CreateCommand parseCreate() throws ParseException {
        expect(TokenType.NEW);
        Token tipo = tokenCorrente;
        avanza();
        TypeConstructorExpr typeConstructor = parseTypeConstructor(tipo);
        PosizioneExpr p = parsePosition();
        return new CreateCommand(typeConstructor, p);
    }

    private TypeConstructorExpr parseTypeConstructor(Token tipo) throws ParseException {
        TypeConstructorExpr tc ;
        expect(TokenType.TONDA_APERTA);

        switch (tipo.getTipo()) {
            case CIRCLE ->{
                float raggio = Float.parseFloat(tokenCorrente.getValore().toString()); //Pos_float per raggio
                tc= new TypeConstructorExpr.CircleConstructor(raggio);
            }
            case RECTANGLE -> {
                float width = Float.parseFloat(tokenCorrente.getValore().toString());
                avanza();
                expect(TokenType.VIRGOLA);
                float height = Float.parseFloat(tokenCorrente.getValore().toString());
                PosizioneExpr p = new PosizioneExpr(width, height); // (base, altezza)
                tc= new TypeConstructorExpr.RectangleConstructor(p);
            }
            case IMG ->{
                String path = (String) tokenCorrente.getValore();
                tc = new TypeConstructorExpr.ImageConstructor(path);
            }
            default -> throw  new ParseException("TipoExpr sconosciuto: "+ tipo.getTipo().toString());
        }
        avanza();
        expect(TokenType.TONDA_CHIUSA);
        return tc;
    }

    private RemoveCommand parseRemove() throws ParseException {
        expect(TokenType.DEL);
        if( tokenCorrente.getTipo() == TokenType.OBJ_ID){
            return new RemoveCommand(tokenCorrente);
        }
        throw new ParseException("Token non è un id.");
    }


    private MoveCommand parseMove() throws ParseException {
        boolean offset = tokenCorrente.getTipo().equals(TokenType.MVOFF);
        avanza();
        Token objId = tokenCorrente;
        avanza();
        PosizioneExpr pos= parsePosition();
        return new MoveCommand(objId, pos, offset);
    }


    private ScaleCommand parseScale() throws ParseException {
        expect(TokenType.SCALE);
        Token objectId = tokenCorrente;
        avanza();
        Token dim= tokenCorrente;
        return new ScaleCommand(objectId, dim);
    }

    private ListCommand parseList() throws ParseException {
        expect(TokenType.LS);
        Token param = tokenCorrente;
        if( paramIsType(param) ){
            TipoExpr tipo = new TipoExpr(param);
            return new ListCommand(tipo);
        }else{
            return new ListCommand(param);
        }
    }

    private GroupCommand parseGroup() throws ParseException {
        expect(TokenType.GRP);
        ListIdExpr listId = parseListId();
        return new GroupCommand(listId);

    }

    private ListIdExpr parseListId() throws ParseException {
       List<Token> objIds = new ArrayList<>();
        while(tokenCorrente.getTipo().equals(TokenType.OBJ_ID )  ){
            objIds.add(tokenCorrente);
            avanza();
            if( tokenCorrente != null ){
                expect(TokenType.VIRGOLA);
            }else{
                break;
            }
        }
        return new ListIdExpr(objIds);

    }

    private UngroupCommand parseUngroup() throws ParseException {
        expect(TokenType.UNGRP);
        Token groupId = tokenCorrente;
        return new UngroupCommand(groupId);
    }

    private AreaCommand parseArea() throws ParseException {
        expect(TokenType.AREA);
        Token param = tokenCorrente;
        if( paramIsType(param) ){
            TipoExpr tipo = new TipoExpr(param);
            return new AreaCommand(tipo);
        }else{
            return new AreaCommand(param);
        }
    }

    private PerimeterCommand parsePerimeter() throws ParseException {
        expect(TokenType.PERIMETER);
        Token param = tokenCorrente;
        if( paramIsType(param) ){
            TipoExpr tipo = new TipoExpr(param);
            return new PerimeterCommand(tipo);
        }else{
            return new PerimeterCommand(param);
        }
    }

    private PosizioneExpr parsePosition() throws ParseException {
        //PosizioneExpr : <pos>::=( <posfloat> , <posfloat> )
        expect(TokenType.TONDA_APERTA);
        String x = tokenCorrente.getValore().toString();
        avanza();
        expect(TokenType.VIRGOLA);
        String y = tokenCorrente.getValore().toString();
        avanza();
        expect(TokenType.TONDA_CHIUSA);
        return new PosizioneExpr(x,y);
    }

    private boolean expect(TokenType tipo) throws ParseException {
        if( tokenCorrente == null || tokenCorrente.getTipo() != tipo ){
            throw new ParseException("Si aspetta un Token di tipo " + tipo + ", ma trovato " + tokenCorrente);
        }
        avanza();
        return true;
    }

    private boolean paramIsType(Token param) {
        return param.getTipo() == TokenType.CIRCLE || param.getTipo() == TokenType.RECTANGLE || param.getTipo() == TokenType.IMG;
    }

    private void avanza() {
        if( tokens.hasNext()){
            tokenCorrente = tokens.next();
        }else{
            tokenCorrente = null;
        }
    }



}
