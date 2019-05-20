package personaje;

import principal.Estado_Juego;
import org.newdawn.slick.SlickException;
import estados.Estado_1_PLAY;

/**
 * Cambia entre mapas y detecta colisiones con NPC y enemigos
 *
 */
public class Portales {

	//---------------------------------------------
	//Metodos
	//---------------------------------------------
	
    public void isEnter(Estado_Juego gameStatus, int playerCenterX, int playerCenterY) throws SlickException {
        for (int i = 0; i < gameStatus.portalMapList.size(); i++) {
            
            if (((Estado_Juego.pos_x_hero >= gameStatus.portalMapList.get(i).xStart) && (Estado_Juego.pos_x_hero <= gameStatus.portalMapList.get(i).xEnd))
                    && ((Estado_Juego.pos_y_hero >= gameStatus.portalMapList.get(i).yStart) && (Estado_Juego.pos_y_hero <= gameStatus.portalMapList.get(i).yEnd))) {
                Estado_Juego.levelID = gameStatus.portalMapList.get(i).levelID;
                Estado_Juego.pos_x_hero = gameStatus.portalMapList.get(i).xNew;
                Estado_Juego.pos_y_hero = gameStatus.portalMapList.get(i).yNew;
                Estado_1_PLAY.needToMapUpdate = true;
            }else if((Estado_Juego.enemys.get(Estado_Juego.levelID)!=null)&&((Estado_Juego.pos_x_hero >= (Estado_Juego.enemys.get(Estado_Juego.levelID).getX_pos()-30))&&(Estado_Juego.pos_x_hero <= (Estado_Juego.enemys.get(Estado_Juego.levelID).getX_pos()+48)))&&((Estado_Juego.pos_y_hero >= Estado_Juego.enemys.get(Estado_Juego.levelID).getY_pos()-30)&&(Estado_Juego.pos_y_hero <= Estado_Juego.enemys.get(Estado_Juego.levelID).getY_pos()+48))){
                System.out.println("---------------------------->>>>He chocado contra enemigo "+Estado_Juego.pos_x_hero+" "+Estado_Juego.pos_y_hero);
                Estado_1_PLAY.dialogo=true;
            }
        }
    }
}
