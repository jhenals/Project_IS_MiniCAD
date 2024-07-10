package MiniCAD.interpreter.utils;

import MiniCAD.interpreter.utils.TokenType;

public class Token {
    private TokenType tipo;
    private Object valore;

    public Token( TokenType tipo, Object valore){
        this.tipo = tipo;
        this.valore = valore;
    }

    public TokenType getTipo(){
        return tipo;
    }

    public Object getValore(){
        return valore;
    }

    public void setTipo(TokenType tipo){
        this.tipo = tipo;
    }

    public void setValore(String valore){
        this.valore = valore;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + tipo +
                ", value=" + valore +
                '}';
    }
}
