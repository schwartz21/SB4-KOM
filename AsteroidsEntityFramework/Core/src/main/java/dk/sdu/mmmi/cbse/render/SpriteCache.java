package dk.sdu.mmmi.cbse.render;


import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;

import java.util.HashMap;

public class SpriteCache {
    private HashMap<SpritePart, TextureAtlas> textureAtlases;
    private HashMap<SpritePart, Sprite> sprites;

    public SpriteCache() {
        this.textureAtlases = new HashMap<>();
        this.sprites = new HashMap<>();
    }

    public Sprite getSprite(SpritePart forThis) {
        Sprite out = sprites.get(forThis);

        if (out != null && forThis.isSpriteTheSame()){
            return out;
        }

        TextureAtlas atlas = textureAtlases.get(forThis);
        if (atlas == null) {
            atlas = new TextureAtlas(new FileHandle(forThis.getAtlasFile()));
//                atlas = new TextureAtlas(Gdx.files.classpath(forThis.getAtlasPath()));
            textureAtlases.put(forThis, atlas);
        }
        out = atlas.createSprite(forThis.getSpriteName());
        sprites.put(forThis, out);
        forThis.setSpriteIsTheSame(true);

        return out;
    }
}
