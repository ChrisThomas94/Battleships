import java.util.HashSet;

/**
 * Created by Chris on 17-Jan-17.
 */
public class Ship {

    int length;
    Boolean isAlive = true;
    HashSet<String> coord = new HashSet<String>();

    public int getLength() {
        return this.length;
    }

    public void setLength(int length){
        this.length = length;
    }

    public void setIsAlive(boolean destroyed){
        this.isAlive = destroyed;
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }

    public void setCoord(HashSet<String> coord){
        this.coord = coord;
    }

    public HashSet<String> getCoord(){
        return coord;
    }

}
