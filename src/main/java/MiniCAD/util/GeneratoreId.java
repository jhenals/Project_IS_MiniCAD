package MiniCAD.util;

public class GeneratoreId {
    private static long counter = 0;

    private GeneratoreId(){}

    public static synchronized String generaId(){
        return "id" + counter++;
    }
    public static synchronized String generaIdGruppo() { return "gid" + counter++;}
}
