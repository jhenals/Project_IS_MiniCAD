package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.Token;
import MiniCAD.interpreter.dataClasses.TokenType;

public class RemoveCommand implements Command {
    private Token id;
    public RemoveCommand(Token id) {
        this.id = id;
    }

    @Override
    public String interpreta() {
        String res = "";
        ObjectManager objectManager= ObjectManager.getInstance();
        if( id.getTipo() == TokenType.OBJ_ID ){
            objectManager.removeObject(id.getValore().toString());
            res = "Rimosso oggetto con id: "+ id;
        }else if( id.getTipo()== TokenType.GRP_ID){
            for( String objId : objectManager.getObjectIDsOfGroup(id.getValore().toString())){
                objectManager.removeObject(objId);
            }
            objectManager.removeObject(id.getValore().toString());
            objectManager.unGroup(id.getValore().toString());
            res ="Rimosso oggetto con id: "+ id.getValore().toString();
        }else{
            res = "Oggetto con id= " + id.getValore().toString() + " non trovato.";
        }
        System.out.println(res);
        return res;
    }



    @Override
    public String toString() {
        return "RemoveCommand{" +
                "id='" + id + '\'' +
                '}';
    }
}
