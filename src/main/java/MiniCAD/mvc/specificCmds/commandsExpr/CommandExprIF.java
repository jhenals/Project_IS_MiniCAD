package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;

public interface CommandExprIF<T> {
    T interpreta(Context context);

}
