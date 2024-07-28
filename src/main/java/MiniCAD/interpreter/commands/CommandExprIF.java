package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;

public interface CommandExprIF<T> {
    T interpreta(Context context);

}
