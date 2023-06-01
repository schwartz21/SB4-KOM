package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

public class Enemy extends SpaceObject {

    private boolean left;
    private boolean right;
    private boolean up;

    private final float maxSpeed;
    private final float acceleration;
    private final float deceleration;

    private final Player player;

    public Enemy(Player player) {

        x = Game.WIDTH / 2f + 50;
        y = Game.HEIGHT / 2f + 50;

        maxSpeed = 300;
        acceleration = 200;
        deceleration = 10;

        shapex = new float[4];
        shapey = new float[4];

        radians = (float) Math.PI / 2;
        rotationSpeed = 3;

        this.player = player;

    }

    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void update(float dt) {
        setUp(false);


        double turn = targetPlayer();
//        System.out.println(turn);

        boolean tempLeft = turn > 0;
        setLeft(tempLeft);
        setRight(!tempLeft);

        // turning
        if (left) {
            radians += rotationSpeed * dt;
        } else if (right) {
            radians -= rotationSpeed * dt;
        }

        // accelerating
        if (up) {
            dx += MathUtils.cos(radians) * acceleration * dt;
            dy += MathUtils.sin(radians) * acceleration * dt;
        }

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if (vec > 0) {
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

        // screen wrap
        wrap();

    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(1, 0, 0, 1);

        sr.begin(ShapeType.Line);

        for (int i = 0, j = shapex.length - 1;
                i < shapex.length;
                j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();

    }

    private double targetPlayer(){
        double xDiff = this.x - player.x;
        double yDiff = this.y - player.y;
        double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

        double targetAngle = Math.acos(xDiff / dist);
        targetAngle = yDiff < 0 ? -targetAngle : targetAngle;
        targetAngle = targetAngle < 0 ? Math.PI * 2 + targetAngle : targetAngle;

//        double anglediff = angle - ((radians + Math.PI) % (Math.PI * 2));
        double thisAngle = ((radians + Math.PI) % (Math.PI * 2));
        thisAngle = thisAngle < 0 ? thisAngle + (Math.PI * 2) : thisAngle;
        double angleDiff = targetAngle - thisAngle;

        angleDiff = angleDiff > Math.PI ? angleDiff - (Math.PI*2) : angleDiff;
        angleDiff = angleDiff < -Math.PI ? angleDiff + (Math.PI*2) : angleDiff;

//        System.out.println("Angle: " + targetAngle);
//        System.out.println("Anglediff: " + angleDiff + " Second part: " + (Math.PI * 2 - angleDiff));



        return Math.abs(angleDiff) > 0.05 ? angleDiff: 0;
//        return Math.min(anglediff, Math.PI * 2 - anglediff);

    }

}
