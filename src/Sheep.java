public class Sheep extends Animal{

    public Sheep(){
        super("sheep");
    }

    public void harvestProduct(Inventory inventory) {
        if (didReachAdulthood()) {
            if (getIsSick()) {
                System.out.println("Ви не можете зібрати шерсть з хворої тварини");
            } else {
                if (!getDidHarvestToday()) {
                    setDidHarvestToday(true);
                    inventory.add("Wool", 1);
                    System.out.println("Шерсть зібрано.");
                } else {
                    System.out.println("Ви сьогодні вже збирали шерсть з цієї тварини.");
                }
            }
        } else {
            System.out.println("Тварина надто молода, щоб збирати з неї шерсть. Зачекайте ще " + (getMaxGrowth() - getGrowthProgress()) + " днів.");
        }
    }
}
