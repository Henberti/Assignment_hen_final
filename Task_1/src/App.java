import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import VehicleWash.VehicleWasher;
import Vehicles.Car;
import Vehicles.MiniBus;
import Vehicles.SUV;
import Vehicles.Truck;

public class App {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
       
        int  washingMachines, carToArraive;
        double timeArraive=1.5;
        double timeWash=3;
        File f = new File("washLogger.txt");

        //get inputs
        System.out.println("How mach car will arraive?");
        carToArraive = s.nextInt();
        System.out.println("How mach washingmachine?");
        washingMachines = s.nextInt();
       

        // creating car washer
        long nextTime = (long) poissonDist(timeArraive);
        long washTime = (long) poissonDist(timeWash);
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

    public static double poissonDist(double lamda){
        return (((-(Math.log(Math.random()))/lamda))*1000);
    }
}

