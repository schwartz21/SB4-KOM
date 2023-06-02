package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

import java.io.*;


public class SpritePart implements EntityPart {
    private static final String ASSET_LOCATION = "Assets";
    private String spriteName;
    private final File atlasFile;

    private boolean spriteIsTheSame = true;


    /**
     * The atlas and the .png that it is pointing to, must be in the same folder.<br>
     *
     * <b>The folder must be opened in the module-info.java file</b>
     * Otherwise, libgdx cannot access the files. <br><br>
     *
     * The recommended approach is to create the same directory/package structure in your resources folder, and then add a subfolder called assets.
     * This subfolder can be opened up exclusively and this allows access to its contents. <br><br>
     *
     * If you follow this structure the atlasName provided would be: <br>
     * <i>assets/yourName.atlas</i>
     *
     * @param instanceOfClassWithResource This class must have a ressources folder with an atlas and corresponding texture present
     * @param atlasName                   Must be including the extension (.atlas)
     * @param spriteName                  The name of the first sprite to use. Must be present in the Atlas
     */
    public SpritePart(Entity instanceOfClassWithResource, String atlasName, String spriteName) {
        File tempAtlasFile;
        atlasFile = createFile(instanceOfClassWithResource, atlasName);

        int extensionIndex = atlasName.lastIndexOf(".");
        String pngName = atlasName.substring(0, extensionIndex) + ".png";
        createFile(instanceOfClassWithResource, pngName);


        this.spriteName = spriteName;
    }

    private static File createFile(Entity instanceOfClassWithResource, String fileName) {
        File out;
        try (InputStream inputStream = instanceOfClassWithResource.getClass().getResourceAsStream(fileName)) {
            // The following two lines strip out the class name
            String[] packageNameParts = instanceOfClassWithResource.getClass().getName().split("\\.");
            String className = packageNameParts[packageNameParts.length - 1];

            out = new File(ASSET_LOCATION + "/" + className + "/" + fileName);
            // Create the directory structure, if it isn't present
            out.getParentFile().mkdirs();

            try (OutputStream outputStream = new FileOutputStream(out)) {
                assert inputStream != null;
                // Copy the contents from the resource into the target folder
                outputStream.write(inputStream.readAllBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out;
    }


    @Override
    public void process(GameData gameData, Entity entity) {
    }

    public String getSpriteName() {
        return spriteName;
    }

    public void changeSprite(String spriteName) {
        this.spriteName = spriteName;
        this.spriteIsTheSame = true;
    }

    public File getAtlasFile() {
        return atlasFile;
    }

    public void setSpriteIsTheSame(boolean newValue){
        this.spriteIsTheSame = newValue;
    }
    public boolean isSpriteTheSame() {
        return spriteIsTheSame;
    }
}
