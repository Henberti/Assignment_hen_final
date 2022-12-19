package Vehicles;
import VehicleWash.VehicleWasher;

public abstract class Vehicle implements Runnable {
    private final int LICENCE;
    private final VehicleWasher WASHER;
    long finishTime;


    public Vehicle(int LICENCE_number, VehicleWasher vehicleWasher){
        this.LICENCE = LICENCE_number;
        this.WASHER = vehicleWasher;
    }

    @Override
    public String toString() {
        return "TYPE: "+this.getClass().getSimpleName()+ " LICENCE: "+LICENCE;
    }

    @Override
    public void run() {
       try {
        WASHER.inline(this);
        WASHER.inWash();
        WASHER.Finish();
        
    } catch (InterruptedException e) {
        
        e.printStackTrace();
    }
        
    }
    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }
    public long getFinishTime() {
        return finishTime;
    }
  




    
}
