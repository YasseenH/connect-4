public class ConnectFourLoop {

    private final ConnectFourGameLogic game;
    @SuppressWarnings("unused")
	private final MainGUI ourGUI;

    public ConnectFourLoop() {
        game = new ConnectFourGameLogic("R", "Y", 6, 7);
        ourGUI = new MainGUI(game.isPlayerOneplaying(), game);
    }

}
