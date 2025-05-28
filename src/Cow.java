public class Cow extends Animal {

    public Cow(){
        super("cow");
    }

    public void harvestProduct(Inventory inventory) {
        if (didReachAdulthood()) {
            if (getIsSick()) {
                System.out.println("Ви не можете зібрати молоко з хворої тварини");
            } else {
                if (!getDidHarvestToday()) {
                    setDidHarvestToday(true);
                    inventory.add("Milk", 1);
                    System.out.println("Молоко зібрано.");
                } else {
                    System.out.println("Ви сьогодні вже збирали молоко з цієї тварини.");
                }
            }
        } else {
            System.out.println("Тварина надто молода, щоб збирати з неї молоко. Зачекайте ще " + (getMaxGrowth() - getGrowthProgress()) + " днів.");
        }
    }
}
