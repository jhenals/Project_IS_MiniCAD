package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;

public class RemoveCommand implements Command {
    private String id;
    public RemoveCommand(String id) {
        this.id = id;
    }

    @Override
    public void interpreta() {
        ObjectManager objectManager= ObjectManager.getInstance();
        objectManager.removeObject(id);
        System.out.println("Rimosso oggetto con id: "+ id);
    }



    @Override
    public String toString() {
        return "RemoveCommand{" +
                "id='" + id + '\'' +
                '}';
    }
}
