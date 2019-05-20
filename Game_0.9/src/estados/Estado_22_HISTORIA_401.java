/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estados;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Sengo
 */

/**
 * 
 * Historia que aparece al derrotar al primer enemigo
 *
 */
public class Estado_22_HISTORIA_401 extends BasicGameState {
	
	//---------------------------------------------
	//Atributos
	//---------------------------------------------
	
    public static final int ID = 22;
    private Image introimg;
    private float posy;
    private final float vel = (float) 0.015;
    private boolean movimiento;
    private int msFinal;
    
	//---------------------------------------------
	//M�todos
	//---------------------------------------------
	

    /**
     * ID de la clase usado para cambiar entre estados
     */
    @Override
    public int getID() {
        return ID;
    }

    /**
     * Crea la historia
     */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        posy = 0;

        introimg = new Image("graphic/historia/intro.png");
        movimiento = true;
        msFinal = 0;
    }

    /**
     * Pinta la historia
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        introimg.draw(0, -posy);
    }

    /**
     * Actualiza la historia y te permite saltartel al pulsar ESC
     * 
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        if (movimiento) {
            if (Keyboard.isKeyDown(Input.KEY_RETURN)) {
                posy += (vel * 2.5) * delta;
            } else {
                posy += (vel) * delta;
            }
            if (posy >= 340) {
                posy = 340;
                movimiento = false;
            }
        } else {

            if (Keyboard.isKeyDown(Input.KEY_RETURN)) {
                msFinal += delta;
            } else {
                msFinal += delta * 2;
            }
            if (posy >= 365) {
                movimiento = false;
            }
        }

        if (Keyboard.isKeyDown(Input.KEY_ESCAPE) || msFinal >= 3000) {
            game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

        }

    }
}
