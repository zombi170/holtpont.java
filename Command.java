public class Command {
    final private String cmd;

    Command(String str){
        cmd = str;
    }

    String getName(){
        if (cmd.length() < 2){
            return null;
        } else{
            return cmd.substring(1);
        }
    }

    int getCmd(){
        if (cmd.length() > 1){
            if (cmd.charAt(0) == '+'){
                return 1;
            } else{
                return -1;
            }
        }
        return 0;
    }
}
