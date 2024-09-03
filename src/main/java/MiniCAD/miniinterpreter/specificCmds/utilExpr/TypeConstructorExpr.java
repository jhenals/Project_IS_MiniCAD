package MiniCAD.miniinterpreter.specificCmds.utilExpr;

import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.commandsExpr.CommandExprIF;

public abstract class TypeConstructorExpr<T> implements CommandExprIF<T> {
    public abstract T interpreta(Context context);

    public static class CircleConstructor extends TypeConstructorExpr<CircleConstructor> {
        private  Token raggio;
        private float r;

        public CircleConstructor(Token raggio) {
                this.raggio = raggio;
            }

            @Override
            public CircleConstructor interpreta(Context context) {
                r = Float.parseFloat(raggio.interpreta(context));
                return this;
            }

            public float getRaggio( ) {
                return r;
            }
    }

    public static class RectangleConstructor extends TypeConstructorExpr<RectangleConstructor> {
        private final PosizioneExpr param; // (base, altezza)
        private float base;
        private float altezza;

        public RectangleConstructor(PosizioneExpr p) {
            this.param = p;
        }

        public float getBase(){ return base; }
        public float getAltezza(){ return altezza; }

        @Override
        public RectangleConstructor interpreta(Context context) {
            base = param.interpreta(context).getParam1();
            altezza = param.interpreta(context).getParam2();
            return this;
        }
    }

    public static class ImageConstructor extends TypeConstructorExpr<ImageConstructor> {
        private CommandExprIF<String> path;
        private String pathStr;

        public ImageConstructor(CommandExprIF<String>  path) {
            this.path = path;
        }
        @Override
        public ImageConstructor interpreta(Context context) {
            pathStr = path.interpreta(context);
            return this;
        }

        public String getPath() {
            return pathStr;
        }


    }

}
