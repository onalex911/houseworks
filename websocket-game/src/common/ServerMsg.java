package common;

import server.GameServer;

public class ServerMsg {
    private String cmd = "";
    private String msg = "";
    private boolean complete;
    public static final String cmdDelim = "::";

    {
        complete = false;
    }
    public ServerMsg(String cmd, String msg) {
        this.cmd = cmd;
        this.msg = msg;
    }

    public ServerMsg(String message) {
        if(message.contains(cmdDelim)) {
            String[] parts = message.split(cmdDelim);
            this.cmd = parts[0];
            this.msg = parts.length > 1 ? message.split(cmdDelim)[1] : "";
        }else{
            this.cmd = "";
            this.msg = message;
        }
    }

    public String getCmd() {
        return cmd;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isCmdExists(){
        return !cmd.isEmpty();
    }

    public String getMessage(){
        return (isCmdExists() ? cmd + cmdDelim : "") + msg;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
