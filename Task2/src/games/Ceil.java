package games;
public class Ceil {//HEN BERTI 201381407 && ELIRAN BALAISH 207598467
    //ceil class holds to cordination of it own 
    //and can hold to type of the sign that the player is choose
    //empty ceil will return the sign "_"
    private Integer[] cordination = new Integer[2];
    private String sign;
   
    public Ceil(Integer ...cordinetion){ 
        this.cordination = cordinetion;
        this.sign = "_";
    }

    public Integer[] getCordinetion(){
        return cordination;
    }

    
    public String PrintCordinate() {
        return "("+cordination[0]+","+cordination[1]+")";
    }

    public boolean isFree(){
        return sign =="_";
    }
    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }
    public String getSign() {
        return sign;
    }

}
