import java.util.ArrayList;
import java.util.Scanner;

public class PastureField extends Field{

    private int cleanliness;
    private ArrayList<Animal> animals;



    public PastureField(){
        super(5, 0,"PastureField");
        this.cleanliness=100;
        animals = new ArrayList<>();
    }

    public int getCleaniness() {
        return cleanliness;
    }

    public void addAnimal(Animal animal){
        if(getSize()>animals.size()){
            animals.add(animal);
            cleanliness -= 1;
            System.out.println("Тварину додано на пасовисько");
        }
        else {
            System.out.println("Це пасовисько переповнене, не вдалося додати тварину до нього");
        }

    }

    public Animal removeAnimal() {
        if (animals.isEmpty()) {
            System.out.println("На пасовищі немає жодної тварини.");
            return null;
        }

        System.out.println("Список тварин на цьому пасовиську:");
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
            System.out.println("Тварину " + removed.getType() + " переміщено з пасовиська.");
            return removed;
        }
    }

    public void maintain(){
        if (cleanliness==100){
            System.out.println("ви не можете почистити повністю чисте пасовисько");
        }
        else{
            cleanliness = 100;
        }
    }

    public void nextDay(){
        for(int i=0;i<animals.size();i++){
            cleanliness-=5;
            animals.get(i).makeSick();
            System.out.println("Тварина "+animals.get(i).getType()+", "+animals.get(i).getAge()+" захворіла");
            animals.get(i).nextDay();
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

    public void sellAnimal(){
        if (animals.isEmpty()) {
            System.out.println("На пасавиську немає жодної тварини.");
        }

        System.out.println("Список тварин на цьому пасовиську:");
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
}
