public abstract class Crop {
    private  String type;
    private  int growthProgress=0;
    private  int maxGrowth;
    private  boolean didWaterToday = false;

    protected Crop(String type, int maxGrowth) {
        this.type = type;
        this.maxGrowth = maxGrowth;
    }

    public String getType() {
        return type;
    }

    public int getGrowthProgress() {
        return growthProgress;
    }

    public int getMaxGrowth() {
        return maxGrowth;
    }

    public boolean getDidWaterToday() {
        return didWaterToday;
    }

    public boolean isReadyToHarvest() {
        return growthProgress == maxGrowth;
    }

    public void water() {
        if (growthProgress == maxGrowth) {
            System.out.println("Ви не можете полити рослину, вона вже дозріла, зберіть врожай.");
        } else if (didWaterToday == true) {
            System.out.println("Ви сьогодні вже поливали рослину.");
        } else {
            growthProgress++;
            didWaterToday = true;
            System.out.println("Рослину полито. До збору врожаю залишилось " + (maxGrowth - growthProgress) + " днів.");
        }

    }

    public void nextDay() {
        didWaterToday = false;
    }

    public static Crop createCrop(String type) {
        switch (type.toLowerCase()) {
            case "tomato":
                return new Tomato();
            case "wheat":
                return new Wheat();
            case "potato":
                return new Potato();
            case "carrot":
                return new Carrot();
            default:
                System.out.println("Невідома рослина");
                return null;
        }
    }
}
