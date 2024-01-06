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

public class PlayerCalcScoreUnit {

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

        // Arrange
        player.setReputationPoints(0);
        player.setArtifacts(new ArrayList<>());

        // Act
        float result = player.calculateScore();

        // Assert
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testCalculateScore_OnlyReputationPoints() {
    	setUp();

        // Arrange
        player.setReputationPoints(5);
        player.setArtifacts(new ArrayList<>());

        // Act
        float result = player.calculateScore();

        // Assert
        assertEquals(50.0, result, 0.0001);
    }

    @Test
    public void testCalculateScore_OnlyArtifacts() {
    	setUp();

        // Arrange
        player.setReputationPoints(0);
        List<Artifact> artifacts = new ArrayList<>();
        artifacts.add(new printingPressArtifact("printingPressArtifact1"));
        artifacts.add(new magicMortarArtifact("magicMortarArtifact2"));
        player.setArtifacts(artifacts);

        // Act
        float result = player.calculateScore();

        // Assert
        assertEquals(1.33, result, 0.0001);
    }

    @Test
    public void testCalculateScore_BothReputationPointsAndArtifacts() {
    	setUp();

        // Arrange
        player.setReputationPoints(3);
        List<Artifact> artifacts = new ArrayList<>();
        artifacts.add(new printingPressArtifact("printingPressArtifact1"));
        artifacts.add(new magicMortarArtifact("magicMortarArtifact2"));
        player.setArtifacts(artifacts);

        // Act
        float result = player.calculateScore();

        // Assert
        assertEquals(31.3333, result, 0.0001);
    }

    @Test
    public void testCalculateScore_LargeValues() {
    	setUp();

        // Arrange
        player.setReputationPoints(100);
        List<Artifact> artifacts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            artifacts.add(new printingPressArtifact("printingPressArtifact" + i));
        }
        player.setArtifacts(artifacts);

        // Act
        float result = player.calculateScore();

        // Assert
        assertEquals(1666.6666, result, 0.0001);
    }

}