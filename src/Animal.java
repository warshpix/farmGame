public abstract class Animal {
    //Клас Animal (абстрактний). Атрибути: вид, вік, здоров'я, продуктивність.
    // Методи: годувати, доїти/стригти, лікувати.

    private String type;
    private int age;
    private boolean isSick;
    private boolean didFeedToday;
    private boolean didHarvestToday;
    private int growthProgress;
    private int adulthoodGrowth;
    private int daysSick;

    protected Animal(String type) {
        this.type = type;
        this.age = 0;
        this.isSick = false;
        this.didFeedToday = false;
        this.didHarvestToday = false;
        this.growthProgress = 0;
        this.adulthoodGrowth = 15;
        this.daysSick = 0;
    }

    public String getType(){
        return type;
    }

    public int getAge(){
        return age;
    }

    public boolean getIsSick(){
        return isSick;
    }

    public void makeSick(){
        isSick = true;
        daysSick = 1;
    }

    public boolean getDidFeedToday(){
        return didFeedToday;
    }

    public boolean getDidHarvestToday(){
        return didHarvestToday;
    }

    public int getGrowthProgress(){
        return growthProgress;
    }

    public int getMaxGrowth(){
        return adulthoodGrowth;
    }

    public boolean didReachAdulthood(){
        return (growthProgress >= adulthoodGrowth);
    }

    public void feed(){
        if(didFeedToday){
            System.out.println("Сьогодні ви вже годували цю тварину");
        }
        else{
            didFeedToday = true;
            growthProgress++;
            System.out.println("Ви погодували цю тварину");
        }
    }

    public void nextDay(){
        age++;
        if(isSick){
            daysSick++;
        }
        didFeedToday = false;
        didHarvestToday = false;
    }

    protected void setDidHarvestToday(boolean state){
        didHarvestToday = state;
    }

    public static Animal createAnimal(String type) {
        switch (type.toLowerCase()) {
            case "cow":
                return new Cow();
            case "sheep":
                return new Sheep();
            default:
                System.out.println("Невідома тварина");
                return null;
        }
    }

    public void setIsSick(boolean b) {
        isSick = b;
    }
}
