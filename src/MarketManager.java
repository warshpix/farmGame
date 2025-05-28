import java.util.HashMap;
import java.util.Map;

public class MarketManager {

    private Map<String, Integer> itemSellPriceList;
    private Map<String, Integer> cropSeedsBuyPrice;

    public MarketManager(){
        this.itemSellPriceList = new HashMap<>();
        itemSellPriceList.put("wheat",3);
        itemSellPriceList.put("carrot",8);
        itemSellPriceList.put("potato",15);
        itemSellPriceList.put("tomato",24);
        itemSellPriceList.put("milk",10);
        itemSellPriceList.put("wool",10);
        this.cropSeedsBuyPrice = new HashMap<>();
        cropSeedsBuyPrice.put("wheat", 1);
        cropSeedsBuyPrice.put("carrot", 2);
        cropSeedsBuyPrice.put("potato", 3);
        cropSeedsBuyPrice.put("tomato", 4);
    }

    public int sellItem(String name, int amount){
        int price = itemSellPriceList.getOrDefault(name,0);
        int moneyToAdd = price * amount;
        if(moneyToAdd == 0){
            System.out.println("Такий об'єкт неможливо продати");
        }
        return moneyToAdd;
    }

    public int buyCropSeeds(String name){
        int price = cropSeedsBuyPrice.getOrDefault(name.toLowerCase(),0);
        if (price == 0){
            System.out.println("Такої рослини не існує");
        }
        return price;
    }

    public void showPrices(){
        int index = 1;
        for (Map.Entry<String, Integer> entry : itemSellPriceList.entrySet()) {
            System.out.println(index + ". " + entry.getKey() + ", " + entry.getValue() + " золота за 1 шт");
            index++;
        }
    }

}
