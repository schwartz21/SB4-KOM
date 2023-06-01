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
        // Create a Mockplayer
        Player underlyingPlayer = new Player();
        Player mockPlayer = mock(Player.class);
        MovingPart mockMovingPart = mock(MovingPart.class);

        // Configure the mockPlayer
        when(mockPlayer.getPart(MovingPart.class)).thenReturn(mockMovingPart);
        when(mockPlayer.getPart(PositionPart.class))
                .thenReturn(underlyingPlayer.getPart(PositionPart.class));
        when(mockPlayer.getShapeX()).thenReturn(underlyingPlayer.getShapeX());
        when(mockPlayer.getShapeY()).thenReturn(underlyingPlayer.getShapeY());
        when(mockPlayer.getRadius()).thenReturn(underlyingPlayer.getRadius());
        when(mockPlayer.getID()).thenReturn("1");

        // Create and configure the mockData
        GameData mockData = mock(GameData.class);
        GameKeys mockKeys = mock(GameKeys.class);
        when(mockData.getKeys()).thenReturn(mockKeys);
        when(mockKeys.isDown(GameKeys.UP)).thenReturn(true);
        when(mockKeys.isDown(GameKeys.LEFT)).thenReturn(false);
        when(mockKeys.isDown(GameKeys.RIGHT)).thenReturn(false);
        when(mockKeys.isDown(GameKeys.DOWN)).thenReturn(false);

        // Create and configure the mockWorld
        World world = mock(World.class);
        List<Entity> players = new ArrayList<>();
        players.add(mockPlayer);
        when(world.getEntities(Player.class)).thenReturn(players);

        // Run the test
        PlayerControlSystem underTest = new PlayerControlSystem();
        underTest.process(mockData, world);

        // Verify the results
        verify(mockPlayer).getPart(MovingPart.class);
        verify(mockMovingPart).setUp(true);
        verify(mockMovingPart).setRight(false);
        verify(mockMovingPart).setLeft(false);
    }
}

