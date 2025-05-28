public abstract class Field {

    private int size;
    private String type;
    private int state;

    protected Field(int size, int state, String type) {
        this.size = size;
        this.state = state;
        this.type = type;
    }

    public static Field createField(String type) {
        switch (type.toLowerCase()) {
            case "cropfield":
                return new CropField();
            case "pasturefield":
                return new PastureField();
            default:
                System.out.println("Невідомий вид поля");
                return null;
        }
    }

    public String getType(){
        return type;
    }
    public void  maintain(){}

    public int getSize(){return size;}

    public int getState(){return state;}

    protected void setState(int state) {
        this.state = state;
    }

    public String getCropType() {
        return "Для цього поля недоступна посадка рослин";
    }
    public void plant(String thing) {
        System.out.println("Це поле не можна засадити.");
    }

    public BoolStringResult harvest() {
        System.out.println("Збір врожаю недоступний для цього типу поля.");
        return null;
    }

    public void nextDay() {
    }

    public void addAnimal(){
        System.out.println("Ви не можете додати тварину до цього типу поля");
    }

    public Animal removeAnimal(){
        System.out.println("Ви не можете забрати тварину з цього типу поля");
        return null;
    }

}
