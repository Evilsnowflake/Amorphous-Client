package engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ClientAffectSelectHandler implements EventHandler<ActionEvent>{

	fxDisplay fxd;
	
	public ClientAffectSelectHandler(fxDisplay fxdd){
		fxd = fxdd;
	}
	@Override
	public void handle(ActionEvent event) {
		CardButton cb = (CardButton)event.getSource();
		GameCommand gc = new GameCommand("affectTarget");
		gc.displayMinion1 = cb.minion;
		gc.displayMinion1.cardPosition = fxd.displayGameState.friendlyFieldMinions.indexOf(cb.minion);
		if(gc.displayMinion1.cardPosition == -1){
			gc.displayMinion1.cardPosition = fxd.displayGameState.enemyFieldMinions.indexOf(cb.minion);
		}
		assert(gc.displayMinion1.cardPosition != -1);
		System.out.println("card position: " + gc.displayMinion1.cardPosition);
		System.out.println("about to send affect select command to server thread");
		fxd.client.write(gc);
	}
}
