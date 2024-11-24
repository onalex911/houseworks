package GetQuotation;

import java.util.ArrayList;

public class Log {
    String userName;
    String timeConnect;
    String timeDisconnect;
    ArrayList<String> quotations;
    {
        quotations = new ArrayList<>();
    }

    public Log(String userName, String timeConnect, String timeDisconnect, ArrayList<String> quotations) {
        this.userName = userName;
        this.timeConnect = timeConnect;
        this.timeDisconnect = timeDisconnect;
        this.quotations = quotations;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Имя: " + userName + "\n" +
                "Время подключения: " + timeConnect + "\n" +
                "Время отключения: " + timeDisconnect + "\n" +
                "Полученные цитаты:\n");
        for (String q : quotations){
            out.append(q).append("\n");
        }
        out.append("---------------------------------------------------------------\n");
        return out.toString();
    }
}
