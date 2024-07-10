package MiniCAD.interpreter.utils;

public abstract class TypeConstructor {
    public static class CircleConstructor extends  TypeConstructor {
        private float raggio;

    public CircleConstructor(float r) {
            raggio = r;
        }

        public float getRaggio() {
            return raggio;
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
    }

    public static class ImageConstructor extends TypeConstructor {
        private String path;

        public ImageConstructor(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

}
