import java.util.HashSet;

/**
 * Created by Chris on 16-Jan-17.
 */
public class destroyer {

    static int length = 4;
    Boolean isAlive = true;
    HashSet<String> pos = new HashSet<String>();

    public int getLength(){
        return this.length;
    }

    public void setIsAlive(boolean destroyed){
        this.isAlive = destroyed;
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }



}
