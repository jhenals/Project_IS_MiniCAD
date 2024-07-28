package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;

public interface CommandIF<T> {
    T interpreta(Context context);
    boolean undo(Context context);

}
