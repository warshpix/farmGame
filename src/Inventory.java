import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private Map<String, Integer> itemList;
    private int maxItems;

    public Inventory() {
        this.itemList = new LinkedHashMap<>();
        this.maxItems = 100;
    }

    public int getMaxItems(){
        return maxItems;
    }

    public void showInventory() {
        System.out.println("Ваш інвентар:");
        if (itemList.isEmpty()) {
            System.out.println("Порожньо.");
            return;
        }
        int i = 1;
        for (Map.Entry<String, Integer> entry : itemList.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(i + ". " + key + ", кількість: " + value);
            i++;
        }
    }

    public boolean doIHave(String name) {
        return itemList.containsKey(name);
    }

    public void sell() {
        showInventory();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Оберіть номер предмету, який ви хочете продати: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > itemList.size()) {
            System.out.println("Невірний вибір. Операцію скасовано.");
            return;
        }

        String itemToSell = (String) itemList.keySet().toArray()[choice - 1];
        int currentQuantity = itemList.get(itemToSell);

        System.out.print("Оберіть кількість предмету, яку ви хочете продати: ");
        int quantity = scanner.nextInt();

        if (quantity < 1) {
            System.out.println("Кількість повинна бути більшою за 0");
        } else if (quantity > currentQuantity) {
            System.out.println("У вас немає стільки предметів. Доступно: " + currentQuantity);
        } else {
            if (quantity == currentQuantity) {
                itemList.remove(itemToSell);
            } else {
                itemList.put(itemToSell, currentQuantity - quantity);
            }

            System.out.println("Продано " + quantity + " шт. \"" + itemToSell + "\".");
        }

    }

    public void add(String name, Integer quantity){
        int currentTotal = itemList.values().stream().mapToInt(Integer::intValue).sum();

        if (currentTotal + quantity > maxItems) {
            System.out.println("Інвентар переповнений! Можна додати не більше " + (maxItems - currentTotal) + " предметів.");
            return;
        }

        itemList.put(name, itemList.getOrDefault(name, 0) + quantity);
        System.out.println("Додано " + quantity + " шт. \"" + name + "\" до інвентарю.");
        return;
    }

    public Map<String, Integer> getInventory(){
        return itemList;
    }

    public void removeItem(String item, int quantity) {
        if (itemList.containsKey(item)) {
            int current = itemList.get(item);
            if (quantity >= current) {
                itemList.remove(item);
            } else {
                itemList.put(item, current - quantity);
            }
        }
    }

}
