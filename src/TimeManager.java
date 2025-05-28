public class TimeManager {
    private int currentDay;
    private String currentSeason;

    public TimeManager(){
        this.currentDay = 1;
        this.currentSeason = "Spring";
    }

    public int getDay(){
        return currentDay;
    }

    public String getSeason(){
        return currentSeason;
    }

    public String getFullDate(){
        return currentDay+" "+currentSeason;
    }

    public void nextDay(){
        if(currentDay+1 > 30){
            nextSeason();
            System.out.println("Розпочався новий сезон");
        }
        else {
            currentDay++;
        }
        System.out.println(getFullDate());
    }

    private void nextSeason(){
        currentDay= 1;
        if(currentSeason.equals("Spring") ){
            currentSeason = "Summer";
        }
        else if (currentSeason.equals("Summer")) {
            currentSeason =  "Autumn";
        }
        else if (currentSeason.equals("Autumn")) {
            currentSeason =  "Winter";
        }
        else {
            currentSeason =  "Spring";
        }
    }
}
