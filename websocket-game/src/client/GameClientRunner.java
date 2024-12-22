package client;

import common.Gesture;
import common.ServerMsg;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLOutput;
import java.util.*;

public class GameClientRunner {
    public static int GamesInMatch = 3;
    public static int DELAY = 1000;
    public static List<Game> matchInfo = new ArrayList<>();

    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < 10; i++) {
//
        String tempName = Player.generateName();
        System.out.println("\nHello, " + tempName + "!");

//        }
        String uri = "ws://localhost:80/ws/chat";
        Session session = null;
        try{
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            GameClient client = new GameClient(tempName);
            session = container.connectToServer(client, URI.create(uri));

            Scanner scn,scn1;

            while(true) {   //основной цикл


                Player player, rival;
                int response = 0;
                while(true) { //устанавливаем режим игры
                    System.out.println("Select game mode");
                    System.out.println("  0 - computer-computer");
                    System.out.println("  1 - human-computer");
                    System.out.println("  2 - human-human");
                    System.out.print("Enter your choice: ");
                    try{
                        response = new Scanner(System.in).nextInt();
                        if(response < 0 || response > 2){
                            System.out.println("Wrong choice! Try again.");
                            continue;
                        }
                        break;
                    }catch(InputMismatchException imc){
                        System.err.println("Wrong choice! (" + imc.getMessage() + ")\nTry again.");
                    }
                }
                player = new Player(tempName,"");

                if(response == 0){
                    player = new Player(tempName + " (computer)","");
                }
//                if(response <= 1){
                    rival = new Player(); //компьютер

//                }else{
//                    rival = client.getFreeUser();
//                }
                System.out.println("Would you play a Match? (y/n)");
                String answer = new Scanner(System.in).nextLine();
                if(answer.equals("y")){
                    for (int i = 0; i < GamesInMatch; i++) {
                        System.out.println("==============================================");
                        System.out.println("                   MATCH                      ");
                        System.out.println("                GAME #" + (i + 1));
                        System.out.printf("Player 1: %s   Player 2: %s\n", player.getName(), rival.getName());
                        System.out.println("==============================================");
                        Game game = new Game(response);
                        doGame(game, player, rival, client,false);
                        matchInfo.add(game);
                    }

                    System.out.println("================== RESULTS OF THE MATCH ==================");

                    int[] winners = new int[3];
                    for (int i = 0; i < GamesInMatch; i++) {
                        Player matchWinner = matchInfo.get(i).getGameWinner();
                        if(matchWinner.equals(player)){
                            winners[0]++;
                        }else if(matchWinner.equals(rival)) {
                            winners[1]++;
                        }else winners[2]++;
                        System.out.println("Game " + (i+1) + ": " + matchWinner.getName());
                    }
                    String absolutWinner = winners[0] > winners[1] ? player.getName() : (winners[1] > winners[0] ? rival.getName() : Game.NOBODY_WINS);
                    System.out.println("Winner of the Match is: " + absolutWinner);
                    if(!absolutWinner.equals(Game.NOBODY_WINS))
                        System.out.println("Congratulations!!!");
                }else {
                    Game singleGame = new Game(response);
                    doGame(singleGame, player, rival, client,false);
                    matchInfo.add(singleGame);
                }
                System.out.println("================== Game statistics ==================");
                getPopularUnpopular(matchInfo);
                System.out.print("\nPlay again? ('n' - no, any key - yes): ");

                if(new Scanner(System.in).nextLine().equals("n")) break;
                matchInfo.clear();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(session != null) session.close();
        }
    }

    private static void doGame(Game game, Player player, Player rival, GameClient client, boolean isMatch) throws InterruptedException {
        boolean doExitGame = false;
        boolean doExitAttempt = false;
        game.startGame();
        Scanner scn = new Scanner(System.in);
        for (int i = 0; i < Game.NumRounds; i++) {
             GameRound gRound = new GameRound(player, rival);

            while (true) { //цикл по попыткам ввода жестов
                System.out.println("-----------------------------------------------");
                System.out.println("                ROUND #" + (i + 1));
                System.out.printf("Player 1: %s   Player 2: %s\n", player.getName(), rival.getName());
                System.out.println("-----------------------------------------------");
                String choice = "";
                if(game.getGameMode() > 0) {
                    System.out.println("  1 - stone");
                    System.out.println("  2 - scissors");
                    System.out.println("  3 - paper");
                    System.out.println("  4 - offer a draw");
                    System.out.println("  999 - I give up");
                    System.out.println("'exit' for quit...");
                    System.out.print("Enter your choice: ");
                }else{
                    System.out.println("'exit' for quit...");
                    System.out.println("Press any key for start.");
                }
                choice = scn.nextLine();

                if (!"exit".equalsIgnoreCase(choice)) {

                    if(game.getGameMode() == 0){
                        Random rndPlayer = new Random();
                        choice = String.valueOf(rndPlayer.nextInt(3) + 1);
                    }

                    switch (choice) {
                        case "1":
                            player.chooseGesture(Gesture.STONE);
                            doExitAttempt = true;
                            break;
                        case "2":
                            player.chooseGesture(Gesture.SCISSORS);
                            doExitAttempt = true;
                            break;
                        case "3":
                            player.chooseGesture(Gesture.PAPER);
                            doExitAttempt = true;
                            break;
                        case "4": game.setDraw(true);
                            doExitGame = true;
                            break;
                        case "999": game.setPlayerGaveUp(true);
                            doExitGame = true;
                            break;
                        default:
                            System.out.println("Answer is not allowed! Try again");
                            continue;
                    }
                } else {
                    doExitGame = true;
                }
                if (doExitGame || doExitAttempt) break;
            }
            if (doExitGame) break;

            System.out.println(player.getName() + " selected " + player.getGestureName());

            client.clearBuffer();
            client.sendMessage(new ServerMsg("MOVE", "").getMessage());
//                    while(!client.isNewMsg()){
            long cnt = 0;
            Thread.sleep(DELAY);
            while (!client.isNewMsg()) {

            }
//                    client.printBuffer();
            if (!client.getNewMsgTxt().isEmpty()) {
                ServerMsg newMsg = new ServerMsg(client.getNewMsgTxt());

                client.clearBuffer();
                client.setNewMsg(false);
                switch (newMsg.getCmd()) {
                    case "MOVE":
                        Gesture[] gestures = Gesture.values();
                        try {
                            rival.chooseGesture(gestures[Integer.parseInt(newMsg.getMsg())]);
                            System.out.println(rival.getName() + " selected " + rival.getGestureName());
                            gRound.play();
                            String resultTxt = gRound.isDraw() ? "DRAW" : "Winner of the round #" + (i + 1) + " is " + gRound.getWinner().getName();
                            System.out.println("\nResult is: " + resultTxt);
                            game.addRoundToGame(gRound);
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                            doExitGame = true;
                        }
                        break;
                    default:
                        System.out.println("Message from server: " + newMsg);
                }
            } else {
                System.out.println("Buffer is empty!");
            }

            if (doExitGame) break;
        }

        if (doExitGame && !game.isDraw() && !game.isPlayerGaveUp()) return;
        String result = "";
        String gameInfo = game.getGameInfo();
        if(game.isDraw()) {
            result = "DRAW";
        }else if(game.isPlayerGaveUp()){
            result = "Winner - %s (%s gave up)\n";
        }else if (game.getGameArraySize() == Game.NumRounds) {
            Player winner = game.getGameWinner();
            if (winner != null)
                result = "Winner - " + winner.getName();
        }

        System.out.println("\n======================= Game result =========================");
        System.out.println(result);
        long[] elps = Game.getPartedTime(game.getGameTime());
        System.out.printf("Elapsed time: %02d:%02d:%02d\n",elps[0],elps[1],elps[2]);
        System.out.println("=========================== History ===========================");
        System.out.print(gameInfo);
        System.out.println("===============================================================");
    }

//    public static String[] getPopularUnpopular(List<Game> matchInfo){
    public static void getPopularUnpopular(List<Game> matchInfo){
        String[] out = new String[2];
        int[] gestArray = new int[Gesture.values().length];
//        for (int i = 0; i < gestArray.length; i++) {
//            gestArray[i] = 0;
//        }
        //подсчет всех жестов в игре
        for (int i = 0; i < matchInfo.size(); i++) {
            Game game = matchInfo.get(i);
            for (int j = 0; j < game.getGameArray().size(); j++) {
                GameRound gRound = game.getGameArray().get(j);
                int[] roundGestures = gRound.getGestures();

                for (int k = 0; k < Gesture.values().length; k++) {
                    if(roundGestures[0] == k)
                        gestArray[k]++;
                    if(roundGestures[1] == k)
                        gestArray[k]++;
                }
            }
        }
        for (int i = 0; i < gestArray.length; i++) {
            System.out.println(Gesture.getNameByPower(i) + ": " + gestArray[i]);
//            System.out.println(gestArray[i]);
        }
//        return out;
    }


}
