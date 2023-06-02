package dk.sdu.mmmi.cbse.playerSystem;

import com.badlogic.gdx.Game;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SuppressWarnings("unchecked")

class PlayerControlSystemTest {
    @Test()
    void process() {
        // Create a TestPlayer
        Player underlyingPlayer = new Player();
        Player testPlayer = mock(Player.class);
        MovingPart testMovingPart = mock(MovingPart.class);

        // Configure the testPlayer
        when(testPlayer.getPart(MovingPart.class)).thenReturn(testMovingPart);
        when(testPlayer.getPart(PositionPart.class))
                .thenReturn(underlyingPlayer.getPart(PositionPart.class));
        when(testPlayer.getShapeX()).thenReturn(underlyingPlayer.getShapeX());
        when(testPlayer.getShapeY()).thenReturn(underlyingPlayer.getShapeY());
        when(testPlayer.getRadius()).thenReturn(underlyingPlayer.getRadius());
        when(testPlayer.getID()).thenReturn("1");

        // Create and configure the testData
        GameData testData = mock(GameData.class);
        GameKeys testKeys = mock(GameKeys.class);
        when(testData.getKeys()).thenReturn(testKeys);
        when(testKeys.isDown(GameKeys.UP)).thenReturn(true);
        when(testKeys.isDown(GameKeys.LEFT)).thenReturn(false);
        when(testKeys.isDown(GameKeys.RIGHT)).thenReturn(false);
        when(testKeys.isDown(GameKeys.DOWN)).thenReturn(false);

        // Create and configure the testWorld
        World testWorld = mock(World.class);
        List<Entity> players = new ArrayList<>();
        players.add(testPlayer);
        when(testWorld.getEntities(Player.class)).thenReturn(players);

        // Run the test
        PlayerControlSystem underTest = new PlayerControlSystem();
        underTest.process(testData, testWorld);

        // Verify the results
        verify(testPlayer).getPart(MovingPart.class);
        verify(testMovingPart).setUp(true);
        verify(testMovingPart).setRight(false);
        verify(testMovingPart).setLeft(false);
    }
}

