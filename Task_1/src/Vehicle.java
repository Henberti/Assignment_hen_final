public abstract class Vehicle implements Runnable {
    private final int LICENSE;
    private final VehicleWasher WASHER;


    public Vehicle(int license_number, VehicleWasher vehicleWasher){
        this.LICENSE = license_number;
        this.WASHER = vehicleWasher;
    }

    @Override
    public String toString() {
        return "TYPE: "+this.getClass().getSimpleName()+ " lICENSE: "+LICENSE;
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




    
}
