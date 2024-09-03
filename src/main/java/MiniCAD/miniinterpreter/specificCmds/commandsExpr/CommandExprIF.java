package MiniCAD.miniinterpreter.specificCmds.commandsExpr;

import MiniCAD.miniinterpreter.specificCmds.Context;

public interface CommandExprIF<T> {
    T interpreta(Context context);

}
