package MiniCAD.interpreter.utilExpr;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandExprIF;

public abstract class TypeConstructorExpr<T> implements CommandExprIF {
    public abstract T interpreta(Context context);

    public static class CircleConstructor extends TypeConstructorExpr {
        private float raggio;

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

    public static class RectangleConstructor extends TypeConstructorExpr {
        private PosizioneExpr param; // (base, altezza)

        public RectangleConstructor(PosizioneExpr p) {
            this.param = p;
        }

        public float getBase(){ return param.getParam1(); }
        public float getAltezza(){ return param.getParam2(); }

        @Override
        public RectangleConstructor interpreta(Context context) {
            return this;
        }
    }

    public static class ImageConstructor extends TypeConstructorExpr {
        private String path;

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
