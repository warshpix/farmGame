public class Silo extends Building{
    private int currentStorage;
    private String itemType;

    public Silo(){
        super(1000, 100, "Silo");
        this.currentStorage = 0;
        this.itemType = null;
    }

    public int getCurrentStorage(){
        return currentStorage;
    }

    public String showItems(){
        if(itemType == null){
            return "Порожньо.";
        }
        else{
            return itemType+" "+currentStorage;
        }
    }

    public boolean isFull(){
        return (this.getSize() == currentStorage);
    }

    public void addItem(String item, int amount){
        if(itemType == null || itemType.equals(item)){
            if (currentStorage + amount > getSize()) {
                System.out.println("Недостатньо місця у силосі.");
                return;
            }
            else {
                this.itemType = item;
                this.currentStorage += amount;
                System.out.println("Рослини додано");
                return;
            }
        }
        else {
            System.out.println("Ця башта зайнята іншим типом рослин");
            return;
        }
    }

    public void removeItem( int amount){
        if(itemType == null){
            System.out.println("Башта порожня");
        }
        else if (currentStorage < amount) {
            System.out.println("В башті менше рослин ніж вимагається");
        }
        else{
            currentStorage-=amount;
            if(currentStorage == 0){
                itemType = null;
            }
        }
    }

    public void nextDay(){
        setStateNextDay();
    }

    public String getItemType(){
        return itemType;
    }
}
