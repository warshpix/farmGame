import java.util.Scanner;

public class FarmSimulator {

    private Farm farm;
    private TimeManager timemanager;
    private MarketManager marketManager;

    public FarmSimulator(){
        this.farm = new Farm();
        this.timemanager = new TimeManager();
        this.marketManager = new MarketManager();
    }

    public void playerAction(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nОберіть дію:");
            System.out.println("1. Дії з полями");
            System.out.println("2. Дії з будівлями");
            System.out.println("3. Дії з тваринами");
            System.out.println("4. Дії з інвентарем");
            System.out.println("5. Показати баланс");
            System.out.println("6. Перейти до наступного дня");
            System.out.println("7. Зберегти гру");
            System.out.println("8. Завантажити гру");
            System.out.println("9. Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Виберіть дію з полем: "+"\n");
                    System.out.println("1. Показати всі поля");
                    System.out.println("2. Створити нове поле: - 100 золота");
                    System.out.println("3. Полити поля");
                    System.out.println("4. Почистити пасовисько - 30 золота");
                    System.out.println("5. Посадити рослину на поле");
                    System.out.println("6. Зібрати врожай");
                    System.out.println("7. Видалити поле");
                    System.out.println("8. Назад"+"\n");
                    int choiceField = scanner.nextInt();
                    scanner.nextLine();

                    switch (choiceField){
                        case 1:
                            System.out.println(farm.getFieldsList());
                            break;
                        case 2:
                            farm.addField();
                            break;
                        case 3:
                            farm.waterCropFields();
                            break;
                        case 4:
                            farm.cleanPastureField();
                            break;
                        case 5:
                            BoolStringResult result = farm.plantSomething();
                            if(result.aBoolean){
                                int price = marketManager.buyCropSeeds(result.aString);
                                int totalMoney = price*25;
                                farm.getFinance().removeMoney(totalMoney);
                                System.out.println("З балансу забрано "+ totalMoney);
                            }
                            break;
                        case 6:
                            BoolStringResult result2 = farm.harvest();
                            if(result2.aBoolean){
                                farm.getInventory().add(result2.aString, 25);
                                System.out.println("До інвентарю додано 25 "+ result2.aString);
                            }
                            break;
                        case 7:
                            farm.removeField();
                            break;
                        case 8:
                            break;

                    }
                    break;


                case 2:
                    System.out.print("Виберіть дію з будівлями: "+"\n");
                    System.out.println("1. Показати всі будівлі");
                    System.out.println("2. Збудувати нову будівлю: - 100 золота");
                    System.out.println("3. Знести будівлю");
                    System.out.println("4. Поремонтувати будівлю: - 30 золота");
                    System.out.println("5. Додати рослину у силос");
                    System.out.println("6. Забрати рослину з силосу");
                    System.out.println("7. Переглянути рослини у силосі");
                    System.out.println("8. Назад"+"\n");
                    int choiceBuilding = scanner.nextInt();
                    scanner.nextLine();

                    switch(choiceBuilding){
                        case 1:
                            System.out.println(farm.getBuildingsList());
                            break;
                        case 2:
                            farm.buildBuilding();
                            break;
                        case 3:
                            farm.removeBuilding();
                            break;
                        case 4:
                            farm.fixBuilding();
                            break;
                        case 5:
                            farm.moveItemToSilo();
                            break;
                        case 6:
                            farm.moveItemFromSilo();
                            break;
                        case 7:
                            farm.showItemsInSilo();
                            break;
                        case 8:
                            break;
                    }

                    break;
                case 3:
                    System.out.print("Виберіть дію з Тваринами: "+"\n");
                    System.out.println("1. Показати тварин у сараях");
                    System.out.println("2. Показати тварин на пасовиськах");
                    System.out.println("3. Купити тварину: - 150 золота");
                    System.out.println("4. Перемістити тварину");
                    System.out.println("5. Продати тварину: + 50 золота");
                    System.out.println("6. Зібрати продукцію");
                    System.out.println("7. Погодувати тварину");
                    System.out.println("8. Назад"+"\n");
                    int choiceAnimal = scanner.nextInt();
                    scanner.nextLine();

                    switch (choiceAnimal){
                        case 1:
                            farm.showAnimalsInBarn();
                            break;
                        case 2:
                            farm.showAnimalsInPasture();
                            break;
                        case 3:
                            farm.addAnimal();
                            break;
                        case 4:
                            System.out.println("Оберіть як саме ви хочете перемістити тварину:");
                            System.out.println("1. З сараю на пасовисько");
                            System.out.println("2. З пасовиська у сарай");
                            int whereToMove = scanner.nextInt();
                            scanner.nextLine();
                            if(whereToMove == 1){
                                farm.moveBarnToPasture();
                            }
                            else if (whereToMove == 2) {
                                farm.movePastureToBarn();
                            }
                            else {
                                System.out.println("Невірна опція, операцію скасовано");
                            }
                            break;
                        case 5:
                            System.out.println("Звідки ви хочете продати Тварину:");
                            System.out.println("1. Пасовисько");
                            System.out.println("2. Сарай");
                            int whereToSellFrom = scanner.nextInt();
                            scanner.nextLine();
                            switch (whereToSellFrom){
                                case 1:
                                    farm.sellFromPasture();
                                    break;
                                case 2:
                                    farm.sellFromBarn();
                                    break;
                            }
                            break;
                        case 6:
                            farm.harvestProduce();
                            break;
                        case 7:
                            farm.feedAnimal();
                            break;
                        case 8:
                            break;
                    }


                    break;
                case 4:
                    System.out.println("Виберіть дію з інвентарем: "+"\n");
                    System.out.println("1. Показати інвентар");
                    System.out.println("2. Показати ціни на ринку");
                    System.out.println("3. Продати предмет з інвентарю");
                    System.out.println("4. Назад");
                    int choiceInventory = scanner.nextInt();
                    scanner.nextLine();

                    switch (choiceInventory){
                        case 1:
                            farm.showInventory();
                            break;
                        case 2 :
                            marketManager.showPrices();
                            break;
                        case 3:
                            StringIntResult item = farm.sellItem();
                            int money = marketManager.sellItem(item.aString, item.aInt);
                            farm.getFinance().addMoney(money);
                            break;
                        case 4:
                            break;
                    }

                    break;
                case 5:
                    System.out.println(farm.getFinance().getBalance());
                    break;
                case 6:
                    farm.nextDay();
                    timemanager.nextDay();
                    System.out.println("наступний день. Поточна дата - "+timemanager.getFullDate());
                    break;
                case 7:
                    saveGame();
                    break;
                case 8:
                    loadGame();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }


    public void saveGame(){
        System.out.println("Гру збережено");
    }

    public void loadGame(){
        System.out.println("Попередню гру завантажено");
    }
}
