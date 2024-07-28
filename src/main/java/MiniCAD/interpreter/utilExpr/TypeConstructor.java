package MiniCAD.interpreter.utilExpr;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandExprIF;

public abstract class TypeConstructor implements CommandExprIF {
    public static class CircleConstructor extends  TypeConstructor {
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

    public static class RectangleConstuctor extends TypeConstructor {
        private Posizione param; // (base, altezza)

        public RectangleConstuctor(Posizione p) {
            this.param = p;
        }

        public Posizione getParametri() {
            return param;
        }

        public float getParam1(){ return param.getParam1(); }
        public float getParam2(){ return param.getParam2(); }

        @Override
        public RectangleConstuctor interpreta(Context context) {
            return this;
        }
    }

    public static class ImageConstructor extends TypeConstructor {
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
