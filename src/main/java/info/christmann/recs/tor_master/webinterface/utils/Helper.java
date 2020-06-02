package info.christmann.recs.tor_master.webinterface.utils;

public class Helper {
    public static double getRandomDoubleBetweenRange(double min, double max){
        double x = (Math.random()*((max-min)+1))+min;
        return x;
    }
}
