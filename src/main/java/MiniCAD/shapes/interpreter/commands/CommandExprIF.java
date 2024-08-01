package MiniCAD.shapes.interpreter.commands;

import MiniCAD.shapes.interpreter.Context;

public interface CommandExprIF<T> {
    T interpreta(Context context);

}
