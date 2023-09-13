import java.util.*;

class Test{
  public static void main(String[] args){
    double[] rates = new double[6];

    double TSC=1.0;
    int s1=1;
    int s2=1;
    rates[0] = 5.0;
    rates[1] = 5.0;
    rates[2] = 5.0;
    rates[3] = 5.0;
    rates[4] = 5.0;
    rates[5] = 5.0;

    System.out.println("================================================");
    System.out.println("Initial Rates: " + Arrays.toString(rates));
    System.out.println("Starting in " + s1 + ", ending in: " + s2);
    double A6 = Markov.getContSejProb(s1,s2,rates,TSC);
    System.out.println("Result: " + A6);
  }
}
