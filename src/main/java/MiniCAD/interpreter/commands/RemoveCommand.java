package MiniCAD.interpreter.commands;

public class RemoveCommand implements Command {
    private String id;
    public RemoveCommand(String id) {
        this.id = id;
    }

    @Override
    public void interpreta() {
        System.out.println("Cancellato oggetto con id "+ id);

        //DELETE LOGIC here
    }


    @Override
    public String toString() {
        return "RemoveCommand{" +
                "id='" + id + '\'' +
                '}';
    }
}
