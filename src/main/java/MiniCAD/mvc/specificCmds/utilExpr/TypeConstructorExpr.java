package MiniCAD.mvc.specificCmds.utilExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.commandsExpr.CommandExprIF;

public abstract class TypeConstructorExpr<T> implements CommandExprIF<T> {
    public abstract T interpreta(Context context);

    public static class CircleConstructor extends TypeConstructorExpr<CircleConstructor> {
        private final float raggio;

        public CircleConstructor(float r) {
                raggio = r;
            }

            public float getRaggio() {
                return raggio;
            }

            @Override
            public CircleConstructor interpreta(Context context) {
                return this;
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
        private final String path;

        public ImageConstructor(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        @Override
        public ImageConstructor interpreta(Context context) {
            return this;
        }
    }

}
