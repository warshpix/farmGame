import java.util.ArrayList;
import java.util.Scanner;

public class Barn extends Building {

    private ArrayList<Animal> animals;

    public Barn(){
        super(10, 100, "Barn");
        this.animals = new ArrayList<>();
    }

    public void nextDay(){
        for (int i=0;i<animals.size();i++){
            animals.get(i).nextDay();
            setStateNextDay();
        }
    }

    public boolean isFull(){
        return (this.getSize() == animals.size());
    }

    public void addAnimal(Animal animal){
        if(isFull()){
            System.out.println("В цьому сараї немає місця для ще одної тварини");
        }
        else{
            animals.add(animal);
            System.out.println("Тварину додано на пасовисько");
        }
    }

    public Animal removeAnimal() {
        if (animals.isEmpty()) {
            System.out.println("В сараї немає жодної тварини.");
            return null;
        }

        System.out.println("Список тварин в цьому сараї:");
        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            System.out.println((i + 1) + ". " + a.getType() + ", вік: " + a.getGrowthProgress());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Оберіть номер тварини, яку потрібно перемістити: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > animals.size()) {
            System.out.println("Невірний вибір. Операцію скасовано.");
            return null;
        }
        else {
            Animal removed = animals.remove(choice - 1);
            System.out.println("Тварину " + removed.getType() + " переміщено з сараю.");
            return removed;
        }
    }

    public void sellAnimal(){
        if (animals.isEmpty()) {
            System.out.println("В сараї немає жодної тварини.");
        }

        System.out.println("Список тварин в цьому сараї:");
        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            System.out.println((i + 1) + ". " + a.getType() + ", вік: " + a.getAge());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Оберіть номер тварини, яку хочете продати: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > animals.size()) {
            System.out.println("Невірний вибір. Операцію скасовано.");
        }
        else {
            Animal removed = animals.remove(choice - 1);
            System.out.println("Тварину " + removed.getType() + " продано.");
        }
    }

    public void feedAnimals(){
        for(int i=0; i<animals.size();i++){
            animals.get(i).feed();
        }
    }

    public ArrayList getAnimalsList(){
        return animals;
    }

    public String showAnimals(){
        String result = "";
        for(int i=0;i<animals.size();i++){
            result+= i+". "+animals.get(i).getType()+", вік: "+animals.get(i).getAge()+"\n";
        }
        return result;
    }

    public BoolStringResult harvestProduce(){
        BoolStringResult result = new BoolStringResult();
        if (animals.isEmpty()) {
            System.out.println("В сараї немає жодної тварини.");
            return null;
        }

        System.out.println("Список тварин в цьому сараї:");
        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            System.out.println((i + 1) + ". " + a.getType() + ", чи збирали продукцію сьогодні: " + a.getDidHarvestToday()+", вік: "+a.getGrowthProgress());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Оберіть номер тварини, з якої хочете зібрати продукцію: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > animals.size()) {
            System.out.println("Невірний вибір. Операцію скасовано.");
            return null;
        }
        else {
            int index = choice-1;
            if(animals.get(index).didReachAdulthood()){
                if(animals.get(index).getIsSick()){
                    System.out.println("Ви не можете зібрати продукцію з хворої тварини");
                    return null;
                }
                else {
                    if(animals.get(index).getDidHarvestToday()){
                        System.out.println("Ви сьогодні вже збирали продукцію з цієї тварини");
                        return null;
                    }
                    else{
                        animals.get(index).setDidHarvestToday(true);
                        result.aBoolean = true;
                        if(animals.get(index).getType().equals("cow")){
                            result.aString = "milk";
                        }
                        else if (animals.get(index).getType().equals("sheep")) {
                            result.aString = "wool";
                        }
                        return result;
                    }
                }
            }
            else{
                System.out.println("Ця тварине не виросла достатньо, щоб з неї збирати продукцію");
                return null;
            }

        }
    }
}
