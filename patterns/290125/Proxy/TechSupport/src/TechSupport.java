import java.util.Random;

//интерфейс сервиса
interface Operator{
    String request = "";
    void setRequest(String request);
    String getResponse();
}

//реализация основного сервиса
class SupportMain implements Operator{
    String request;

    @Override
    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String getResponse() {
        System.out.println("Поиск ответа в базе знаний...\n");
        Random rnd = new Random();
        int wait = rnd.nextInt(100000000 - 10000) + 10000;
        for (int i = 0; i < wait; i++) {

        }
        return "Текст ответа";
    }
}

//реализация заместителя
class SupportHelper implements Operator{
    String request;
    private final Operator service = new SupportMain();
    @Override
    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String getResponse() {
        String response = checkInCache(request);
        if(response.isEmpty()) {
            response = checkInRecent(request);
            putToCache(request, response);
        }
        if(response.isEmpty()){
            service.setRequest(request);
            response = service.getResponse();
            putToCache(request,response);
            putToRecent(request,response);
        }
        return response;
    }
    //список вопросов, заданных в течение месяца
    private String checkInRecent(String request){
        String response;
        Random rnd1 = new Random();
        if(rnd1.nextBoolean()) {
            Random rnd2 = new Random();
            int wait = rnd2.nextInt(10000000 - 1000) + 1000;
            System.out.println("Поиск в списке недавно заданных ...\n");
            for (int i = 0; i < wait; i++) {

            }
            response = "Текст ответа из списка недавно заданных";
        }else response = "";
        return response;
    }
    //список вопросов, заданных в течение дня
    private String checkInCache(String request){
        Random rnd = new Random();
        return  rnd.nextBoolean() ? "Текст ответа из кэша" : "";
    }

    void putToRecent(String request, String response){
        System.out.println("Поместили в вопрос в список недавно заданных");
    }
    void putToCache(String request,String response){
        System.out.println("Поместили в вопрос в кэш");
    }
}

//класс запуска сервиса для клиентской части
class SupportManager{
    protected Operator service;

    public SupportManager(String request) {
        this.service = new SupportHelper();
        this.service.setRequest(request);
    }
    public String getResponse() {
        return service.getResponse();
    }
}

public class TechSupport {
    public static void main(String[] args) {
        String question = "текст вопроса";
        System.out.println("Вопрос в техподдержку: " + question);

        SupportManager sm = new SupportManager(question);
        System.out.println(sm.getResponse());
    }
}
