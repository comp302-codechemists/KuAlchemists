package Business;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.time.LocalDateTime;  

public class GameEvent {
	
	enum EventID {
		JOIN_GAME,
		PAUSE_GAME,
		RESUME_GAME,
		LEVEL_UP,
		FORAGE_INGREDIENT,
		TRANSMUTE_INGREDIENT,
		BUY_ARTIFACT,
		MAKE_EXPERIMENT,
		PUBLISH_THEORY,
		FINISH_GAME,
		START_GAME,
		DEBUNK_THEORY
	}
	
	private String eventTime;
	private EventID eventID;
	private KUAlchemistsGame game;
	private Player player;
	
	private static List<GameEvent> events = new ArrayList<>();
	
	/*
	 * Create and log new event like this:
	 * GameEvent events = new GameEvent(game, player, GameEvent.EventID.EVENT_ID);
	 */
	
	// Constructor gets current time, creates event with player and event id and adds it to the event list.
	public GameEvent(KUAlchemistsGame game, Player player, EventID eventID) {
		
		eventTime = getTime();		
		this.eventID = eventID;
		this.game = game;
		this.player = player;
		addEvent(this);
	}
	
	private String getTime() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		eventTime = dtf.format(now);
		return eventTime;	
		
	}
	
	public Color getColor() {
		
		if (player == null) { //System event, no player is attached.
			return Color.white;
		}
		
		else if (player.getAvatarPath().equals("avatar1")) {
			return Color.green;
		}
		
		else if (player.getAvatarPath().equals("avatar2")) {
			return Color.blue;
		}
		
		else if (player.getAvatarPath().equals("avatar3")) {
			return Color.cyan;
		}
		
		else { // avatar4
			return Color.orange;
		}
	}
	
	public String getEventString() {
		String s = "";
		switch (eventID) {
		
			case JOIN_GAME:
				s = String.format("%s: %s joined the game!", eventTime, player.getUserName());
				break;
				
			case PAUSE_GAME:
				s = String.format("%s: Game is paused.", eventTime);
				break;
				
			case RESUME_GAME:
				s = String.format("%s: Game is resumed.", eventTime);
				break;
			
			case LEVEL_UP:
				s = String.format("%s: Level up! New level: %d", eventTime, game.getRound());
				break;
				
			case FORAGE_INGREDIENT:
				s = String.format("%s: %s foraged for an ingredient.", eventTime, player.getUserName());
				break;
			
			case TRANSMUTE_INGREDIENT:
				s = String.format("%s: %s transmuted an ingredient.", eventTime, player.getUserName());
				break;
			
			case BUY_ARTIFACT:
				s = String.format("%s: %s bought an artifact.", eventTime, player.getUserName());
				break;
				
			case MAKE_EXPERIMENT:
				s = String.format("%s: %s made an experiment.", eventTime, player.getUserName());
				break;
			
			case PUBLISH_THEORY:
				s = String.format("%s: %s published a theory.", eventTime, player.getUserName());
				break;
				
			case FINISH_GAME:
				s = String.format("%s: Game finished. Winner: %s.", eventTime, player.getUserName());
				break;
				
			case START_GAME:
				s = "Game has started. Level: 1";
				break;
				
			case DEBUNK_THEORY:
				s = String.format("%s: %s tried to debunk a theory.", eventTime, player.getUserName());
				break;
				
			default:
				s = "Unknown event.";
			
		}		
		
		return s;
	}
	
	
	public static List<GameEvent> getEvents() {
		return events;
	}
	
	private static void addEvent(GameEvent event) {
		events.add(event);
	}
	
	


	
}