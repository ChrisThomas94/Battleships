/**
 * Created by Chris on 16-Jan-17.
 */
public class battleship {

    static int length = 5;
    String[] coord;
    String[][] coord1;
    String[][] coord2;
    String[][] coord3;
    String[][] coord4;
    String[][] coord5;
    Boolean isAlive = true;

    public int getLength() {
        int length = this.length;
        return length;
    }

    public void setIsAlive(boolean destroyed){
        this.isAlive = destroyed;
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }

    public void setCoord(String[] coord){
        this.coord = coord;
    }

    public void setCoord1(String[][] coord){
        this.coord1 = coord;
    }

    public String[][] getCoord1(){
        return this.coord1;
    }

    public void setCoord2(String[][] coord){
        this.coord2 = coord;
    }

    public String[][] getCoord2(){
        return this.coord2;
    }

    public void setCoord3(String[][] coord){
        this.coord3 = coord;
    }

    public String[][] getCoord3(){
        return this.coord3;
    }

    public void setCoord4(String[][] coord){
        this.coord4 = coord;
    }

    public String[][] getCoord4(){
        return this.coord4;
    }

    public void setCoord5(String[][] coord){
        this.coord5 = coord;
    }

    public String[][] getCoord5(){
        return this.coord5;
    }

}
