package MiniCAD.interpreter.lexerparser;

public enum TokenType {

    //Parole Riservate
    NEW, DEL, MV, MVOFF, SCALE, LS, ALL, GROUPS, GRP, UNGRP, AREA, PERIMETER,

    //Tipo Constraints
    CIRCLE, RECTANGLE, IMG,


    //Simboli
    TONDA_APERTA, TONDA_CHIUSA, VIRGOLA, QUOTE,

    //Identifiers
    PATH, POS_FLOAT, OBJ_ID,

    CHAR_INVALIDO,

}
