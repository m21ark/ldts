package birdrun.state;

public class Command {
    COMMAND command;
    Character key;

    public Command() {
        this.key = ' ';
        this.command = COMMAND.NONE;
    }

    public Character getKey() {
        return key;
    }

    public COMMAND getCommandEnum() {
        return command;
    }

    public enum COMMAND {UP, RIGHT, DOWN, LEFT, PAUSE, QUIT, SELECT, NONE}

}
