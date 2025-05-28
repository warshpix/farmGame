public abstract class Building {
    private String type;
    private int size;
    private int state;

    protected Building(int size, int state, String type) {
        this.size = size;
        this.state = state;
        this.type = type;
    }

    public static Building createBuilding(String type) {
        switch (type.toLowerCase()) {
            case "silo":
                return new Silo();
            case "barn":
                return new Barn();
            default:
                System.out.println("Невідомий вид будівлі.");
                return null;
        }
    }

    public String getType(){
        return type;
    }

    public int getSize(){
        return size;
    }

    public int getState(){
        return state;
    }

    public void setStateNextDay(){
        state-=1;
    }

    public void repair(){
        if(state == 100){
            System.out.println("ви не можете поремонтувати не поламану будівлю");
        }
        else {
            //відняти 50 за ремонт
            state = 100;
            System.out.println("будівлю поремонтовано");
        }
    }

    public void nextDay(){
        setStateNextDay();
    }

    public boolean isFull(){
        return false;
    }

    public void addPlant(Crop crop, int amount){
        System.out.println("До цього типу будівлі не можна додати рослину");
    }

    public void removePlant(Crop crop, int amount){
        System.out.println("З цього типу будівлі не можна забрати рослину");
    }

    public void addAnimal(Animal animal){
        System.out.println("До цього типу будівлі не можна додати тварину");
    }

    public Animal removeAnimal(){
        System.out.println("З цього типу будівлі не можна забрати тварину");
        return null;
    }

    public void feedAnimals(Animal animal){
        System.out.println("В цьому типі будівлі не можна годувати тварин");
    }
}
