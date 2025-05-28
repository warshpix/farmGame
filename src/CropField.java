public class CropField extends Field {
    private Crop croptype;

    public CropField() {
        super(25, 0, "CropField");
        this.croptype = null;
    }

    public String getCropType() {
        if(croptype == null || croptype.getType() == null){
            return "на полі нічого не висаджено";
        }
        else{
            return croptype.getType();
        }
    }

    public void maintain() {
        if(croptype == null){
            System.out.println("На полі нічого не висаджено");
        }
        else{
            croptype.water();
        }
    }

    public void plant(String crop) {
        if(croptype == null){
            this.croptype = Crop.createCrop(crop);
            setState(1);
            System.out.println("ви посадили " + getSize() + " грядки " + croptype.getType());
        }
        else{
            System.out.println("На цьому полі вже щось росте");
        }

    }

    public BoolStringResult harvest() {
        boolean didHarvest = false;
        String cropp  = "";
        if(croptype == null){
            System.out.println("На цьому полі нічого не росте");
        }
        else{
            if (croptype.isReadyToHarvest() == true) {
                System.out.println("Зібрано " + getSize() + " " + croptype.getType());
                didHarvest = true;
                cropp = croptype.getType();
                croptype = null;
                setState(0);
            }
            else{
                System.out.println("Ваша рослина не дозріла. зачекайте ще " + (croptype.getMaxGrowth()-croptype.getGrowthProgress()) + " днів");
            }
        }
        BoolStringResult result = new BoolStringResult(didHarvest, cropp);
        return result;
    }

    public void nextDay() {
        if (croptype != null) {
            croptype.nextDay();
        }
    }

}
