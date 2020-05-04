package model.invoker;

import model.command.Command;

public class Commander {
    Command command;
    public Commander(){

    }

    public void setCommand(Command command){
        this.command = command;
    }

    public void executeCommand(){
        command.execute();
    }
}
