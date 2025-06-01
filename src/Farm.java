import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Farm {

    private ArrayList<Field> fieldsList;
    private ArrayList<Building> buildingsList;
    private ArrayList<Animal> animalsList;
    private Inventory inventory;
    private Finance finance;

    public Farm() {
        this.fieldsList = new ArrayList<>();
        this.buildingsList = new ArrayList<>();
        this.animalsList = new ArrayList<>();
        this.inventory = new Inventory();
        this.finance = new Finance();
        Field field1 = new CropField();
        Field field2 = new PastureField();
        fieldsList.add(field1);
        fieldsList.add(field2);
        Building building1 = new Barn();
        Building building2 = new Silo();
        buildingsList.add(building1);
        buildingsList.add(building2);
    }

    public void addField() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Оберіть тип поля, яке хочете додати:");
        System.out.println("1. Сільськогосподарське поле (CropField)");
        System.out.println("2. Пасовище (PastureField)");
        System.out.print("Ваш вибір (1 або 2): ");

        int choice = scanner.nextInt();
        String type = null;

        switch (choice) {
            case 1:
                type = "CropField";
                break;
            case 2:
                type = "PastureField";
                break;
            default:
                System.out.println("Невірний вибір. Операцію скасовано.");
                return;
        }

        Field field = Field.createField(type);
        if (field != null) {
            int cost = 100;
            if (finance.removeMoney(cost)) {
                fieldsList.add(field);
                System.out.println("Витрачено 100 грн на будівлю.");
                System.out.println("Додано поле: " + field.getType());
            } else {
                System.out.println("Не вистачає коштів для купівлі поля.");
            }
        } else {
            System.out.println("Не вдалося створити поле.");
        }
    }

    public void removeField() {
        if (fieldsList.isEmpty() || fieldsList.size()==1) {
            System.out.println("Немає жодного поля для видалення.");
            return;
        }

        System.out.println("Список полів:");
        for (int i = 0; i < fieldsList.size(); i++) {
            System.out.print((i + 1) + ". " + fieldsList.get(i).getType());
            if(fieldsList.get(i) instanceof PastureField){
                PastureField pasture = (PastureField) fieldsList.get(i);
                System.out.println(", кількість тварин - "+pasture.getAnimalsList().size()+"/10");
            }
            else if(fieldsList.get(i) instanceof CropField){
                CropField field = (CropField) fieldsList.get(i);
                System.out.println(", "+field.getCropType());
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Оберіть номер поля, яке ви хочете видалити: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > fieldsList.size()) {
            System.out.println("Невірний вибір. Операцію скасовано.");
            return;
        }

        Field removedField = fieldsList.remove(choice - 1);
        System.out.println("Поле типу \"" + removedField.getType() + "\" було видалено.");
    }

    public void removeBuilding() {
        if (buildingsList.isEmpty() || buildingsList.size()==1) {
            System.out.println("Немає жодної будівлі для видалення.");
            return;
        }

        System.out.println("Список будівель:");
        for (int i = 0; i < buildingsList.size(); i++) {
            System.out.println((i + 1) + ". " + buildingsList.get(i).getType());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Оберіть номер будівлі, яку ви хочете видалити: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > buildingsList.size()) {
            System.out.println("Невірний вибір. Операцію скасовано.");
            return;
        }

        Building removedBuilding = buildingsList.remove(choice - 1);
        System.out.println("Поле типу \"" + removedBuilding.getType() + "\" було видалено.");
    }

    public void buildBuilding() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Оберіть тип будівлі, яку хочете збудувати:");
        System.out.println("1. Склад (Silo)");
        System.out.println("2. Хлів (Barn)");
        System.out.print("Ваш вибір (1 або 2): ");

        int choice = scanner.nextInt();
        String type;

        switch (choice) {
            case 1:
                type = "silo";
                break;
            case 2:
                type = "barn";
                break;
            default:
                System.out.println("Невірний вибір. Операцію скасовано.");
                return;
        }

        Building building = Building.createBuilding(type);
        if (building != null) {
            int cost = 100;
            if (finance.removeMoney(cost)) {
                buildingsList.add(building);
                System.out.println("Збудовано будівлю: " + building.getType());
                System.out.println("Витрачено " + cost + " грн на будівлю.");
            } else {
                System.out.println("Недостатньо коштів для побудови будівлі.");
            }
        } else {
            System.out.println("Не вдалося створити будівлю.");
        }
    }

    public void addAnimal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Оберіть тварину, яку хочете додати:");
        System.out.println("1. Корова (Cow)");
        System.out.println("2. Вівця (Sheep)");
        System.out.print("Ваш вибір (1 або 2): ");

        int choice = scanner.nextInt();
        String type;

        switch (choice) {
            case 1:
                type = "cow";
                break;
            case 2:
                type = "sheep";
                break;
            default:
                System.out.println("Невірний вибір. Операцію скасовано.");
                return;
        }

        Animal animal = Animal.createAnimal(type);
        if (animal != null) {
            if (finance.getBalance()>=150) {
                ArrayList<Barn> barns = new ArrayList<>();
                ArrayList<Integer> barnsID = new ArrayList<>();
                System.out.println("Оберіть cарай щоб додати у нього тварину:"+"\n");
                int i = 0;
                int j = 0;
                for(Building building : buildingsList){
                    if(building instanceof Barn){
                        Barn barn = (Barn) building;
                        System.out.println(i+". "+ barn.getType()+ ", місткість: "+ barn.getAnimalsList().size()+ "/10"+ "\n");
                        barns.add(barn);
                        barnsID.add(j);
                        i++;
                    }
                    j++;
                }

                int choice2 = scanner.nextInt();
                scanner.nextLine();
                if(0<=choice2 && choice2<barns.size()){
                    int position = barnsID.get(choice2);
                    buildingsList.get(position).addAnimal(animal);
                    animalsList.add(animal);
                    System.out.println("Додано тварину: " + animal.getType());
                    finance.removeMoney(150);
                    System.out.println("Витрачено 150 грн на тварину.");
                }
                else {
                    System.out.println("Введено невірний номер, спробуйте знову");
                }

            } else {
                System.out.println("Недостатньо коштів для додавання тварини.");
            }
        } else {
            System.out.println("Не вдалося додати тварину.");
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void showInventory(){
        inventory.showInventory();
    }

    public Finance getFinance() {
        return finance;
    }

    public String getFieldsList() {
        int i=1;
        String result = "";
        for(Field field : fieldsList){
            result += i+ ". "+field.getType() + "\n";
            i++;
        }
        return result;
    }

    public String getBuildingsList() {
        int i=1;
        String result = "";
        for(Building building : buildingsList){
            result += i+ ". "+building.getType() + "\n";
            i++;
        }
        return result;
    }

    public ArrayList<Animal> getAnimalsList() {
        return animalsList;
    }

    public void nextDay(){
        for (int i=0; i<fieldsList.size();i++){
            fieldsList.get(i).nextDay();
        }
        for (int i=0; i<animalsList.size();i++){
            animalsList.get(i).nextDay();
        }
        for(int i=0; i< buildingsList.size();i++){
            buildingsList.get(i).nextDay();
            if(buildingsList.get(i).getState() == 0){
                System.out.println(buildingsList.get(i).getType()+" зруйновано через занедбаність");
                buildingsList.remove(i);
            }
        }
        System.out.println("Наступний день");
    }

    public void waterCropFields(){
        for(Field field : fieldsList){
            if(field instanceof CropField){
                field.maintain();
            }
        }
    }

    public void cleanPastureField(){
        ArrayList<PastureField> pastureFields = new ArrayList<>();
        ArrayList<Integer> pastureFieldId = new ArrayList<>();
        System.out.println("Оберіть поле щоб почистити:"+"\n");
        int i = 0;
        int j = 0;
        for(Field field : fieldsList){
            if(field instanceof PastureField){
                PastureField pasture = (PastureField) field;
                int clean = pasture.getCleaniness();
                System.out.println(i+". "+ field.getType()+ ", чистота: "+ clean+ "\n");
                pastureFields.add(pasture);
                pastureFieldId.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<pastureFields.size()){
            int position = pastureFieldId.get(choice);
            fieldsList.get(position).maintain();
            finance.removeMoney(30);
            System.out.println("Пасовисько почищено");
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
        }
    }

    public BoolStringResult plantSomething(){
        boolean isPlanted = false;
        String plantType = "";
        ArrayList<CropField> cropFields = new ArrayList<>();
        ArrayList<Integer> cropFieldsId = new ArrayList<>();
        System.out.println("Оберіть поле щоб засадити:"+"\n");
        int i = 0;
        int j = 0;
        for(Field field : fieldsList){
            if(field instanceof CropField){
                CropField crop = (CropField) field;
                String status = "";
                if(crop.getState()==1){
                    status = "Засаджено";
                }
                else{
                    status = "Порожньо";
                }
                System.out.println(i+". "+ field.getType()+ ", статус: "+ status+ "\n");
                cropFields.add(crop);
                cropFieldsId.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<cropFields.size()){
            int position = cropFieldsId.get(choice);
            System.out.println("Культури на вибір:");
            System.out.println("1. Пшениця");
            System.out.println("2. Морква");
            System.out.println("3. Картопля");
            System.out.println("4. Помідори");
            int choice2 = scanner.nextInt();
            scanner.nextLine();
            String plant = "";
            switch (choice2){
                case 1:
                    fieldsList.get(position).plant("Wheat");
                    break;
                case 2:
                    fieldsList.get(position).plant("Carrot");
                    break;
                case 3:
                    fieldsList.get(position).plant("Potato");
                    break;
                case 4:
                    fieldsList.get(position).plant("Tomato");
                    break;
                default:
                    System.out.println("Невірний тип культури");
                    break;
            }
            if(fieldsList.get(position).getState() == 1){
                isPlanted = true;
            }
            plantType = fieldsList.get(position).getCropType();
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
        }
        BoolStringResult result = new BoolStringResult(isPlanted, plantType);
        return result;
    }

    public BoolStringResult harvest(){
        BoolStringResult result = new BoolStringResult();
        ArrayList<CropField> cropFields = new ArrayList<>();
        ArrayList<Integer> cropFieldsId = new ArrayList<>();
        System.out.println("Оберіть поле щоб зібрати врожай:"+"\n");
        int i = 0;
        int j = 0;
        for(Field field : fieldsList){
            if(field instanceof CropField){
                CropField crop = (CropField) field;
                String status = "";
                String crops = "";
                if(crop.getState()==1){
                    status = "Засаджено";
                    crops = crop.getCropType();
                }
                else{
                    status = "Порожньо";
                }
                System.out.println(i+". "+ field.getType()+ ", статус: "+ status+ " " +crops+ "\n");
                cropFields.add(crop);
                cropFieldsId.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<cropFields.size()){
            int position = cropFieldsId.get(choice);
            result = fieldsList.get(position).harvest();

        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
        }
        return result;
    }

    public void fixBuilding(){
        System.out.println("Оберіть будівлю щоб поремонтувати:"+"\n");
        int i = 0;
        for(Building building : buildingsList){
            System.out.println(i+". "+ building.getType()+ ", стан: "+ building.getState()+ "\n");
            i++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(finance.getBalance()>=30){
            if(0<=choice && choice<buildingsList.size()){
                buildingsList.get(choice).repair();
                finance.removeMoney(30);
                System.out.println("30 золота було забрано з вашого рахунку за ремонт будівлі.");
            }
            else {
                System.out.println("Введено невірний номер, спробуйте знову");
            }
        }
        else {
            System.out.println("У вас недостатньо коштів для цієї дії");
        }
    }

    public void showAnimalsInBarn(){
        ArrayList<Barn> barns = new ArrayList<>();
        ArrayList<Integer> barnsID = new ArrayList<>();
        System.out.println("Оберіть сарай, тварин якого ви хочете переглянути:"+"\n");
        int i = 0;
        int j = 0;
        for(Building building : buildingsList){
            if(building instanceof Barn){
                Barn barn = (Barn) building;
                System.out.println(i+". "+ barn.getType()+ ", місткість: "+ barn.getAnimalsList().size()+ "/10"+ "\n");
                barns.add(barn);
                barnsID.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<barns.size()){
            if(barns.get(choice).getAnimalsList().size() == 0){
                System.out.println("Сарай порожній");
            }
            else{
                System.out.println(barns.get(choice).showAnimals());
            }

        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
        }
    }

    public void showAnimalsInPasture(){
        ArrayList<PastureField> pastures = new ArrayList<>();
        ArrayList<Integer> pastureID = new ArrayList<>();
        System.out.println("Оберіть пасовисько, тварин якого ви хочете переглянути:"+"\n");
        int i = 0;
        int j = 0;
        for(Field field : fieldsList){
            if(field instanceof PastureField){
                PastureField pasture = (PastureField) field;
                System.out.println(i+". "+ pasture.getType()+ ", місткість: "+ pasture.getAnimalsList().size()+ "/10"+ "\n");
                pastures.add(pasture);
                pastureID.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<pastures.size()){
            if(pastures.get(choice).getAnimalsList().size() == 0){
                System.out.println("Сарай порожній");
            }
            else{
                System.out.println(pastures.get(choice).showAnimals());
            }

        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
        }
    }

    public void moveBarnToPasture(){
        int positionOfBarn = -1;
        int positionOfPasture = -1;
        ArrayList<Barn> barns = new ArrayList<>();
        ArrayList<Integer> barnsID = new ArrayList<>();
        System.out.println("Оберіть сарай з якого хочете забрати тварину:"+"\n");
        int i = 0;
        int j = 0;
        for(Building building : buildingsList){
            if(building instanceof Barn){
                Barn barn = (Barn) building;
                System.out.println(i+". "+ barn.getType()+ ", заповненість: "+ barn.getAnimalsList().size()+"/10"+ "\n");
                barns.add(barn);
                barnsID.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<barns.size()){
            positionOfBarn = barnsID.get(choice);
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
            return;
        }

        ArrayList<PastureField> pastures = new ArrayList<>();
        ArrayList<Integer> pasturesID = new ArrayList<>();
        System.out.println("Оберіть пасовисько, на яке ви хочете перемістити тварину:"+"\n");
        i = 0;
        j = 0;
        for(Field field : fieldsList){
            if(field instanceof PastureField){
                PastureField pasture = (PastureField) field;
                System.out.println(i+". "+ pasture.getType()+ ", заповненість: "+ pasture.getAnimalsList().size()+"/10"+ "\n");
                pastures.add(pasture);
                pasturesID.add(j);
                i++;
            }
            j++;
        }

        int choice2 = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice2 && choice2<pastures.size()){
            positionOfPasture = pasturesID.get(choice2);
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
            return;
        }

        Barn moveBarn = (Barn) buildingsList.get(positionOfBarn);
        PastureField movePasture = (PastureField) fieldsList.get(positionOfPasture);
        Animal animalToMove = moveBarn.removeAnimal();
        movePasture.addAnimal(animalToMove);

    }

    public void movePastureToBarn(){
        int positionOfBarn = -1;
        int positionOfPasture = -1;
        ArrayList<PastureField> pastures = new ArrayList<>();
        ArrayList<Integer> pasturesID = new ArrayList<>();
        System.out.println("Оберіть пасовисько з якого хочете забрати тварину:"+"\n");
        int i = 0;
        int j = 0;
        for(Field field : fieldsList){
            if(field instanceof PastureField){
                PastureField pasture = (PastureField) field;
                System.out.println(i+". "+ pasture.getType()+ ", заповненість: "+ pasture.getAnimalsList().size()+"/10"+ "\n");
                pastures.add(pasture);
                pasturesID.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<pastures.size()){
            positionOfPasture = pasturesID.get(choice);
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
            return;
        }

        ArrayList<Barn>  barns = new ArrayList<>();
        ArrayList<Integer> barnsID = new ArrayList<>();
        System.out.println("Оберіть сарай, у який ви хочете перемістити тварину:"+"\n");
        i = 0;
        j = 0;
        for(Building building : buildingsList){
            if(building instanceof Barn){
                Barn barn = (Barn) building;
                System.out.println(i+". "+ barn.getType()+ ", заповненість: "+ barn.getAnimalsList().size()+"/10"+ "\n");
                barns.add(barn);
                barnsID.add(j);
                i++;
            }
            j++;
        }

        int choice2 = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice2 && choice2<barns.size()){
            positionOfBarn = barnsID.get(choice2);
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
            return;
        }

        Barn moveBarn = (Barn) buildingsList.get(positionOfBarn);
        PastureField movePasture = (PastureField) fieldsList.get(positionOfPasture);
        Animal animalToMove = movePasture.removeAnimal();
        moveBarn.addAnimal(animalToMove);
    }

    public void sellFromPasture(){
        ArrayList<PastureField> pastureFields = new ArrayList<>();
        ArrayList<Integer> pastureFieldsID = new ArrayList<>();
        System.out.println("Оберіть пасовисько з якого ви хочете продати тварину:"+"\n");
        int i = 0;
        int j = 0;
        for(Field field : fieldsList){
            if(field instanceof PastureField){
                PastureField pasture = (PastureField) field;
                System.out.println(i+". "+ field.getType()+ ", статус: "+ pasture.getAnimalsList().size()+ "/10 \n");
                pastureFields.add(pasture);
                pastureFieldsID.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<pastureFields.size()){
            int position = pastureFieldsID.get(choice);
            PastureField pastureChanged = (PastureField) fieldsList.get(position);
            pastureChanged.sellAnimal();
            fieldsList.set(position, pastureChanged);
            finance.addMoney(50);
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
        }
    }

    public void sellFromBarn(){
        ArrayList<Barn> barns = new ArrayList<>();
        ArrayList<Integer> barnsID = new ArrayList<>();
        System.out.println("Оберіть сарай з якого ви хочете продати тварину:"+"\n");
        int i = 0;
        int j = 0;
        for(Building building : buildingsList){
            if(building instanceof Barn){
                Barn barn = (Barn) building;
                System.out.println(i+". "+ building.getType()+ ", статус: "+ barn.getAnimalsList().size()+ "/10 \n");
                barns.add(barn);
                barnsID.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<barns.size()){
            int position = barnsID.get(choice);
            Barn barnChanged = (Barn) buildingsList.get(position);
            barnChanged.sellAnimal();
            buildingsList.set(position, barnChanged);
            finance.addMoney(50);
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
        }
    }

    public void harvestProduce(){
        ArrayList<Barn> barns = new ArrayList<>();
        ArrayList<Integer> barnsID = new ArrayList<>();
        System.out.println("Оберіть сарай з якого ви хочете вибрати тварину щоб зібрати продукцію:"+"\n");
        int i = 0;
        int j = 0;
        for(Building building : buildingsList){
            if(building instanceof Barn){
                Barn barn = (Barn) building;
                System.out.println(i+". "+ building.getType()+ ", статус: "+ barn.getAnimalsList().size()+ "/10 \n");
                barns.add(barn);
                barnsID.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<barns.size()){
            int position = barnsID.get(choice);
            Barn barnChanged = (Barn) buildingsList.get(position);
            BoolStringResult result = barnChanged.harvestProduce();
            if(result != null){
                buildingsList.set(position, barnChanged);
                inventory.add(result.aString,1);
                System.out.println("Продукцію зібрано і додано у інвентар");

            }
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
        }
    }

    public StringIntResult sellItem(){
        Map<String, Integer> items = inventory.getInventory();
        StringIntResult result = new StringIntResult();
        System.out.println("Виберіть предмет який ви хочете продати:");
        showInventory();
        Scanner scanner = new Scanner(System.in);
        int item = scanner.nextInt();
        scanner.nextLine();
        if(item<1 || item>inventory.getInventory().size()){
            System.out.println("Невірно вибраний номер, спробуйте знову.");
            return null;
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(items.entrySet());
        Map.Entry<String, Integer> selectedEntry = entryList.get(item - 1);
        String itemName = selectedEntry.getKey();
        int currentAmount = selectedEntry.getValue();

        System.out.println("Виберіть скільки одиниць ви хочете продати:");
        int amountToSell = scanner.nextInt();
        scanner.nextLine();

        if (amountToSell <= 0 || amountToSell > currentAmount) {
            System.out.println("Неправильна кількість для продажу.");
            return null;
        }
        inventory.removeItem(itemName, amountToSell);
        System.out.println("Продано " + amountToSell + " одиниць " + itemName + ".");
        result.aInt = amountToSell;
        result.aString = itemName;
        return result;
    }

    public void moveItemToSilo(){
        Map<String, Integer> items = inventory.getInventory();
        System.out.println("Виберіть предмет який ви хочете перемістити:");
        showInventory();

        Scanner scanner = new Scanner(System.in);
        int item = scanner.nextInt();
        scanner.nextLine();

        if(item < 1 || item > items.size()){
            System.out.println("Невірно вибраний номер, спробуйте знову.");
            return;
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(items.entrySet());
        Map.Entry<String, Integer> selectedEntry = entryList.get(item - 1);
        String itemName = selectedEntry.getKey();
        int currentAmount = selectedEntry.getValue();

        System.out.println("Виберіть скільки одиниць ви хочете перемістити:");
        int amountToSell = scanner.nextInt();
        scanner.nextLine();

        if (amountToSell <= 0 || amountToSell > currentAmount) {
            System.out.println("Неправильна кількість для переміщення.");
            return;
        }

        int positionOfSilo = -1;
        ArrayList<Silo> silos = new ArrayList<>();
        ArrayList<Integer> silosID = new ArrayList<>();
        System.out.println("Оберіть силос, у який ви хочете додати предмети:"+"\n");
        int i = 0;
        int j = 0;
        for(Building building : buildingsList){
            if(building instanceof Silo){
                Silo silo = (Silo) building;
                System.out.println(i+". "+ silo.getType()+ ", заповненість: "+ silo.getCurrentStorage()+"/"+silo.getSize()+ "\n");
                silos.add(silo);
                silosID.add(j);
                i++;
            }
            j++;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<silos.size()){
            positionOfSilo = silosID.get(choice);
            Silo silo = (Silo) buildingsList.get(positionOfSilo);
            silo.addItem(itemName, amountToSell);
            inventory.removeItem(itemName, amountToSell);
            buildingsList.set(positionOfSilo, silo);
            System.out.println("Предмети переміщено успішно");
            }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
            return;
        }

    }

    public void moveItemFromSilo(){
        int positionOfSilo = -1;
        ArrayList<Silo> silos = new ArrayList<>();
        ArrayList<Integer> silosID = new ArrayList<>();
        System.out.println("Оберіть силос, з якого хочете забрати предмети:"+"\n");
        int i = 0;
        int j = 0;
        for(Building building : buildingsList){
            if(building instanceof Silo){
                Silo silo = (Silo) building;
                System.out.println(i+". "+ silo.getType()+ ", заповненість: "+ silo.getCurrentStorage()+"/"+silo.getSize()+ "\n");
                silos.add(silo);
                silosID.add(j);
                i++;
            }
            j++;
        }
        Scanner scanner = new Scanner(System.in);
        int siloChoice = scanner.nextInt();
        scanner.nextLine();
        if(0<=siloChoice && siloChoice<silos.size()){
            positionOfSilo = silosID.get(siloChoice);
            Silo silo = (Silo) buildingsList.get(positionOfSilo);
            System.out.println("В цьому силосі:");
            System.out.println(silo.showItems());
            System.out.println("Скільки шт ви хочете перемістити в інвентар?");
            int amountItems = scanner.nextInt();
            scanner.nextLine();
            if(amountItems<1 || amountItems>silo.getCurrentStorage()){
                System.out.println("Введено неправильнц кількість");
                return;
            }
            else {
                inventory.add(silo.getItemType(),amountItems);
                Silo silo2 = (Silo) buildingsList.get(positionOfSilo);
                silo2.removeItem(amountItems);
                buildingsList.set(positionOfSilo,silo2);
                System.out.println("Успішно");

            }

        }

        else {
            System.out.println("Введено невірний номер, спробуйте знову");
            return;
        }
    }

    public void showItemsInSilo(){
        int positionOfSilo = -1;
        ArrayList<Silo> silos = new ArrayList<>();
        ArrayList<Integer> silosID = new ArrayList<>();
        System.out.println("Оберіть силос, у який ви хочете додати предмети:"+"\n");
        int i = 0;
        int j = 0;
        for(Building building : buildingsList){
            if(building instanceof Silo){
                Silo silo = (Silo) building;
                System.out.println(i+". "+ silo.getType()+ ", заповненість: "+ silo.getCurrentStorage()+"/"+silo.getSize()+ "\n");
                silos.add(silo);
                silosID.add(j);
                i++;
            }
            j++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(0<=choice && choice<silos.size()){
            positionOfSilo = silosID.get(choice);
            Silo silo = (Silo) buildingsList.get(positionOfSilo);
            System.out.println(silo.showItems());
        }
        else {
            System.out.println("Введено невірний номер, спробуйте знову");
            return;
        }
    }

    public void feedAnimal(){
        System.out.println("Оберіть тварину, яку хочете погодувати:"+"\n");
        for(Animal animal : animalsList){
            int i=0;
            System.out.println(i+". "+ animal.getType()+ ", вік: "+ animal.getGrowthProgress()+ "\n");
            i++;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice<0 || choice>=animalsList.size()){
            System.out.println("Невірно введений індекс");
        }
        else{
            animalsList.get(choice).feed();
        }
    }

}
