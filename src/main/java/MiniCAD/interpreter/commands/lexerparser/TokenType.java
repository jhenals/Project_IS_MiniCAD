package MiniCAD.interpreter.commands.lexerparser;

public enum TokenType {

    //Parole Riservate
    NEW, DEL, MV, MVOFF, SCALE, IMG, LS, ALL, GROUPS, GRP, UNGRP, AREA, PERIMETER,

    //Simboli
    TONDA_APERTA, TONDA_CHIUSA, VIRGOLA, QUOTE,

    //Identifiers
    PATH, POS_FLOAT, OBJ_ID,

    CHAR_INVALIDO,

}
