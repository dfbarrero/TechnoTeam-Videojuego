package modelos;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import personaje.Ataque;
import principal.SpriteGeneral;

/**
 * Constructor base para los enemigos
 * Estadísticas básicas de los enemigos
 * 
 */

public class Enemy {
	
    private int vida, vidaMax;
    private String enemyName;
    private int IdLevel;
    private ArrayList<Ataque> ataques;
    private int x_pos = 0;
    private int y_pos = 0;
    private String source;
    private SpriteGeneral sprite;
    private Image HUD;
 
    public Enemy() {}

    //Con interfaz de combate
    public Enemy(int vida,String enemyName, int IdLevel, SpriteGeneral sprite, int x_pos, int y_pos, String source,Image HUD) {
        this.vidaMax = vida;
        this.vida = vida;
    	this.enemyName = enemyName;
        this.IdLevel=IdLevel;
        ataques = new ArrayList<>();
        this.sprite = sprite;
        this.x_pos=x_pos;
        this.y_pos=y_pos;
        this.source = source;
        this.HUD=HUD;
    }
    
    //Sin interfaz de combate
    public Enemy(String enemyName, int IdLevel, SpriteGeneral sprite, int x_pos, int y_pos, String source) {
    	this.enemyName = enemyName;
        this.IdLevel=IdLevel;
        this.sprite = sprite;
        this.x_pos=x_pos;
        this.y_pos=y_pos;
        this.source = source;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public void setIdLevel(int IdLevel) {
        this.IdLevel = IdLevel;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSprite(SpriteGeneral sprite) {
        this.sprite = sprite;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getIdLevel() {
        return IdLevel;
    }

    public String getSource() {
        return source;
    }

    public SpriteGeneral getSprite() {
        return sprite;
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }
   
}
