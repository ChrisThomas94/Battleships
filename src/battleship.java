/**
 * Created by Chris on 16-Jan-17.
 */
public class battleship {

    static int length = 5;

    Boolean isAlive = true;

    public int getLength() {
        return this.length;
    }

    public void setIsAlive(boolean destroyed){
        this.isAlive = destroyed;
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }

}
