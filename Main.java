import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static Task readTask(String sor, LinkedList<Resource> resourcelist){
        LinkedList<Command> queue = new LinkedList<>();

        String[] in = sor.split(",");
        String name = in[0];
        boolean found;

        for (String i : in){
            if (!i.equals(name)){
                Command cmd = new Command(i);
                queue.add(cmd);
                found = false;

                for (Resource r : resourcelist){
                    if (r.getName().equals(cmd.getName())){
                        found = true;
                    }
                }

                if (cmd.getName() != null && !found){
                    resourcelist.add(new Resource(cmd.getName()));
                }
            }
        }

        return new Task(name, queue);
    }

    public static String getResource(LinkedList<Resource> resourcelist, String output, Task t, Command cmd) {
        for (Resource r : resourcelist){
            if (r.getName().equals(cmd.getName())){
                // ha szabad
                if (r.isFree()){
                    r.setTask(t);
                    // ha foglalt
                } else{
                    t.setResource(r);
                    Task temp_t = null;
                    Resource temp_r = r;
                    while (temp_r != null && temp_r.getTask() != null && temp_t != t){
                        temp_t = temp_r.getTask();
                        temp_r = temp_t.getResource();
                    }
                    // holtpont
                    if (temp_t == t){
                        t.setNull();
                        output = output.concat(t.getName() + "," + t.getIndex() + "," + r.getName() + "\n");
                    } else{
                        r.addWaitingTask(t);
                    }
                }
            }
        }
        return output;
    }

    public static void freeResource(LinkedList<Resource> resourcelist, Task t, Command cmd) {
        for (Resource r : resourcelist){
            if (r.getName().equals(cmd.getName()) && r.getTask() == t){
                r.setNull();
                // ha van varakozo task
                if (r.hasWaitingTask()){
                    Task temp = r.popWaitingTask();
                    temp.setNull();
                    r.setTask(temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Task> tasklist = new LinkedList<>();
        LinkedList<Resource> resourcelist = new LinkedList<>();
        Scanner scan = new Scanner("T1,+R1,+R1");
        while(scan.hasNextLine()){
            String sor = scan.nextLine();
            if (!sor.equals("")){
                tasklist.add(readTask(sor, resourcelist));
            }
        }
        scan.close();

        String output = "";
        LinkedList<Task> finished = new LinkedList<>();

        while (!tasklist.isEmpty()){
            for (Task t : tasklist){
                if (!t.isWaiting() && !t.isFinished()){
                    Command cmd = t.getCommand();
                    // lefoglalas
                    if (cmd.getCmd() == 1){
                        output = getResource(resourcelist, output, t, cmd);
                        // felszabaditas
                    } else if (cmd.getCmd() == -1){
                        freeResource(resourcelist, t, cmd);
                    }
                    // ha vege a tasknak
                } else if (t.isFinished()){
                    LinkedList<Command> fifo = t.getFifo();
                    int i = 0;
                    while (i != fifo.size()){
                        Command cmd = fifo.get(i++);
                        if (cmd.getCmd() == 1){
                            freeResource(resourcelist, t, cmd);
                        }
                    }
                    finished.add(t);
                }
            }
            tasklist.removeAll(finished);
            finished.clear();
        }

        System.out.println(output);
    }
}