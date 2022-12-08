import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
       
        int  washingMachines, carToArraive;
        long timeArraive=1500, timeWash=3000;
        File f = new File("washLogger.txt");

        //get inputs
        System.out.println("how mach car will arraive?");
        carToArraive = s.nextInt();
        System.out.println("how mach washingmachine?");
        washingMachines = s.nextInt();
      


        // creating car washer
        long nextTime =(long)((((-Math.log(Math.random()))/timeArraive))*1000);
        long washTime = (long)((((-Math.log(Math.random()))/timeWash))*1000);
        VehicleWasher vw = new VehicleWasher(carToArraive, washingMachines, nextTime, washTime, f);




        ArrayList<Thread> vehicles = new ArrayList<>();


        for(int i=0; i<carToArraive; i++){
            int type =(int) ((Math.random()*4)+1);
            int license = (int) ((Math.random()+1)*1000000000);

            switch(type){
                case 1:
                    vehicles.add(new Thread(new Car(license, vw)));
                break;

                case 2:
                    vehicles.add(new Thread(new MiniBus(license, vw)));
                break;

                case 3:
                    vehicles.add(new Thread(new Truck(license, vw)));
                break;

                case 4:
                    vehicles.add(new Thread(new SUV(license, vw)));
            }
        }

        vehicles.forEach(vehicle->{
            vehicle.start();
            

        });
        s.close();
      

        
       
   


      



       



        
    }
}
