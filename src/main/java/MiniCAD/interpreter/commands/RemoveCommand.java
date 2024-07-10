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
    public void interpreta() {
        ObjectManager objectManager= ObjectManager.getInstance();
        if( id.getTipo() == TokenType.OBJ_ID ){
            objectManager.removeObject(id.getValore().toString());
            System.out.println("Rimosso oggetto con id: "+ id);
        }else if( id.getTipo()== TokenType.GRP_ID){
            for( String objId : objectManager.getObjectIDsOfGroup(id.getValore().toString())){
                objectManager.removeObject(objId);
            }
            objectManager.removeObject(id.getValore().toString());
            objectManager.unGroup(id.getValore().toString());
            System.out.println("Rimosso oggetto con id: "+ id);
        }else{
            System.out.println("Oggetto con id= " + id.getValore().toString() + " non trovato.");
        }

    }



    @Override
    public String toString() {
        return "RemoveCommand{" +
                "id='" + id + '\'' +
                '}';
    }
}
