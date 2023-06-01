import java.util.LinkedList;

public class Task {
    final private String name;
    final private LinkedList<Command> fifo;
    private Resource resource;
    private int index;

    Task(String name, LinkedList<Command> fifo){
        this.name = name;
        this.fifo = fifo;
        resource = null;
        index = 0;
    }

    void setResource(Resource resource){
        this.resource = resource;
    }

    Command getCommand(){
        if (index < fifo.size()){
            return fifo.get(index++);
        } else{
            return null;
        }
    }

    Resource getResource(){
        return resource;
    }

    String getName(){
        return name;
    }

    int getIndex(){
        return index;
    }

    boolean isWaiting(){
        return resource != null;
    }

    void setNull(){
        resource = null;
    }

    boolean isFinished(){
        return index == fifo.size() && resource == null;
    }

    LinkedList<Command> getFifo(){
        return fifo;
    }
}
