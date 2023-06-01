import java.util.LinkedList;

public class Resource {
    final private String name;
    private LinkedList<Task> waitlist;
    private Task task;

    Resource(String name){
        this.name = name;
        waitlist = new LinkedList<>();
        task = null;
    }

    void setTask(Task task){
        this.task = task;
    }

    boolean isFree(){
        return task == null;
    }

    void setNull(){
        task = null;
    }

    Task getTask(){
        return task;
    }

    String getName(){
        return name;
    }

    void addWaitingTask(Task task){
        waitlist.add(task);
    }

    boolean hasWaitingTask(){
        return !waitlist.isEmpty();
    }

    Task popWaitingTask(){
        return waitlist.pop();
    }
}
