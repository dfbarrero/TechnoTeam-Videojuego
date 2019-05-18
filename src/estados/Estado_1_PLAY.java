package estados;

import java.awt.Font;
import jdk.nashorn.internal.ir.BreakNode;
import personaje.Evento;
import principal.Estado_Juego;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import modelos.Frase;

public class Estado_1_PLAY extends BasicGameState {

    public static boolean needToMapUpdate = false;

    public static Estado_Juego gameStatus;

    private Evento event;
    int i = 1;

    private Image ventanaDialogo, avatarDialogo;
    private static final int POSICIONAVATARX = 30;
    private static final int POSICIONAVATARY = 600;

    public static boolean dialogo = false;
    public static boolean pelea = false;
    private TrueTypeFont personaje, frase;
    private Font tipo_letra_dialogo = new Font("Arial Black", Font.PLAIN, 15);

    public Estado_1_PLAY() {
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        gameStatus = new Estado_Juego();
        event = new Evento(Estado_Juego.sprite);
        ventanaDialogo = new Image("graphic/dialogo/cajaTexto.png");

        personaje = new TrueTypeFont(tipo_letra_dialogo, true);
        frase = new TrueTypeFont(tipo_letra_dialogo, true);

    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {

        Input input = container.getInput();
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();

        event.update(container, sbg, delta, gameStatus, input, xPos, yPos);

        if (needToMapUpdate) {
            updateGameStatus(container);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
        Input input = container.getInput();
        try {
            gameStatus.map = new TiledMap("graphic/map/" + Integer.toString(Estado_Juego.levelID) + ".tmx");
            gameStatus.map.render(0, 0, 0);
            gameStatus.updateEntityFieldList(gameStatus.map);
        } catch (SlickException e) {
            System.out.println("Error en updateGameStatus playState");
        }
        Estado_Juego.sprite.avatar.draw(Estado_Juego.pos_x_hero, Estado_Juego.pos_y_hero);
        if (Estado_Juego.enemys.get(Estado_Juego.levelID) != null) {
            Estado_Juego.enemys.get(Estado_Juego.levelID).getSprite().avatar.draw(Estado_Juego.enemys.get(Estado_Juego.levelID).getX_pos(), Estado_Juego.enemys.get(Estado_Juego.levelID).getY_pos());
        }
        renderDialogo(input);
    }

    public void updateGameStatus(GameContainer container) {
        needToMapUpdate = false;
        gameStatus.updatePortalMapList(gameStatus.portalMapList);
    }

    private void renderDialogo(Input input) throws SlickException {
        Frase f = Estado_Juego.dialogo.get((Estado_Juego.levelID * 10) + i);
        if (dialogo && Estado_Juego.dialogo.get((Estado_Juego.levelID * 10) + i) != null) {
            this.ventanaDialogo.draw(0, 620, 1);

            if (f.getPersonaje().equalsIgnoreCase("Hero")) {
                avatarDialogo = new Image("graphic/dialogo/" + Estado_Juego.hero.getIdHero() + ".png");
                personaje.drawString(160, 625, Estado_Juego.hero.getHeroName());
            } else {
                avatarDialogo = new Image("graphic/dialogo/" + f.getIdPersonaje() + ".png");
                personaje.drawString(160, 625, f.getPersonaje());
            }
            this.avatarDialogo.draw(POSICIONAVATARX, POSICIONAVATARY, 1);
            frase.drawString(160, 645, f.getFrase());
            if (input.isKeyPressed(Input.KEY_SPACE)) {
                i++;
            }
        } else if (Estado_Juego.dialogo.get((Estado_Juego.levelID * 10) + i) == null) {
            dialogo = false;
            System.out.println("Inserte aquí pelea");
        }
    }
}
