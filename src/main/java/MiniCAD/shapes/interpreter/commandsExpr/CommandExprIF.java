package MiniCAD.shapes.interpreter.commandsExpr;

import MiniCAD.shapes.interpreter.Context;

public interface CommandExprIF<T> {
    T interpreta(Context context);

}
