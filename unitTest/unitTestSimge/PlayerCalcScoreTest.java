package unitTestSimge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Business.Artifact;
import Business.Player;
import Business.MagicMortarArtifact;
import Business.PrintingPressArtifact;
/**
 * Calculates the player's score based on reputation points and artifacts.
 * 
 * Requires:
 *   * None.
 * Modifies:
 *   * None.
 * Effects:
 *   *	  Player's score will be calculated based on:
 *   	  1 artifact -> 2 gold, 3 gold -> 1 score point => 1 artifact -> 2/3 score point.
 *        score = (reputationPoints * 10) + (totalArtifacts player has * 2 / 3)
 *   A float will be returned as the calculated score.
 */

public class PlayerCalcScoreTest {

	private Player player;

    @Test
    public void setUp() {
        // Initialize a Player object for testing
        player = new Player("TestPlayer", "avatar.png");
    }
    
    public float calculateScore() {
    	setUp();
        int score = 0;
        score += player.getReputationPoints() * 10;
        // 1 artifact -> 2 gold, 3 gold -> 1 score point => 1 artifact -> 2/3 score point.
        score += player.getArtifacts().size() * 2 / 3; 
        return score;
    }

    @Test
    public void testCalculateScore_MinimumValues() {
    	setUp();

        player.setReputationPoints(0);
        player.setArtifacts(new ArrayList<>());

        int result = (int)player.calculateScore();

        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testCalculateScore_OnlyReputationPoints() {
    	setUp();

        player.setReputationPoints(5);
        player.setArtifacts(new ArrayList<>());

        int result = (int)player.calculateScore();

        assertEquals(50.0, result, 0.0001);
    }

    @Test
    public void testCalculateScore_OnlyArtifacts() {
    	setUp();

        player.setReputationPoints(0);
        List<Artifact> artifacts = new ArrayList<>();
        artifacts.add(new PrintingPressArtifact("printingPressArtifact1"));
        artifacts.add(new MagicMortarArtifact("magicMortarArtifact2"));
        player.setArtifacts(artifacts);

        int result = (int)player.calculateScore();

        assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testCalculateScore_BothReputationPointsAndArtifacts() {
    	setUp();

        player.setReputationPoints(3);
        List<Artifact> artifacts = new ArrayList<>();
        artifacts.add(new PrintingPressArtifact("printingPressArtifact1"));
        artifacts.add(new MagicMortarArtifact("magicMortarArtifact2"));
        player.setArtifacts(artifacts);

        int result = (int)player.calculateScore();

        assertEquals(31.0, result, 0.0001);
    }

    @Test
    public void testCalculateScore_LargeValues() {
    	setUp();

        player.setReputationPoints(100);
        List<Artifact> artifacts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            artifacts.add(new PrintingPressArtifact("printingPressArtifact" + i));
        }
        player.setArtifacts(artifacts);

        int result = (int)player.calculateScore();

        assertEquals(1666.0, result, 0.0001);
    }

}
