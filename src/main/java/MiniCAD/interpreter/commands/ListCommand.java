package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;
import MiniCAD.interpreter.lexerparser.TokenType;

public class ListCommand implements Command{
    private Token parametro;

    public ListCommand(Token parametro) {
        this.parametro = parametro;
    }

    public Token getParametro() {
        return parametro;
    }

    public void setParametro(Token parametro) {
        this.parametro = parametro;
    }

    //TODO
    @Override
    public void interpreta() {
        if( parametro.getTipo() == TokenType.OBJ_ID){
            //visualizza le proprietà dell’oggetto identificato da id1 o l’elenco degli oggetti parte del gruppo identificato da id1
        } else if (parametro.getTipo() == TokenType.CIRCLE) {
            //visualizza l’elenco degli oggetti di tipo circle
        } else if (parametro.getTipo() == TokenType.RECTANGLE) {
            //visualizza l’elenco degli oggetti di tipo rectangle
        } else if (parametro.getTipo() == TokenType.IMG) {
            //visualizza l’elenco degli oggetti di tipo img
        } else if (parametro.getTipo() == TokenType.ALL) {
            //visualizza l’elenco degli oggetti di tipo circle
        } else if (parametro.getTipo() == TokenType.GROUPS) {
            //visualizza l’elenco di tutti i gruppi di oggetti
        } else {
        }

    }


    @Override
    public void undo() {

    }

    @Override
    public String toString() {
        return "ListCommand{" +
                "parametro=" + parametro +
                '}';
    }
}
