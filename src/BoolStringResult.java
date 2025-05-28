public class BoolStringResult {
    public boolean aBoolean;
    public String aString;

    public BoolStringResult(boolean aBoolean, String aString){
        this.aString = aString;
        this.aBoolean = aBoolean;
    }

    public BoolStringResult(){
        this.aString = "";
        this.aBoolean = false;
    }
}
