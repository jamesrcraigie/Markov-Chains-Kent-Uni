import java.util.*;

class Test1{
  public static void main(String[] args){
    int s1, s2, numStates, TS;
    double TSC;
    double[] ssprob  = new double[9];
    double[] rates   = new double[6];
    Random ran = new Random();

    // ==============================================
    // ============ Q1 ==============================
    // ==============================================

    numStates = 9;
    System.out.println("================================");
    System.out.println("Running question 1");
    for (int i = 1; i <= numStates; i++){
      for (int j = 1; j <= numStates; j++){
        s1 = i;
        s2 = j;
        System.out.println("S1: " + s1 + ", S2: " + s2);
        Markov.getTransProb(i, j, numStates);
      }
    }

    // ==============================================
    // ============ Q2 ==============================
    // ==============================================

    numStates = 4;
    System.out.println("================================");
    System.out.println("Running question 2");
    for (int i = 1; i <= numStates; i++){
      for (int j = 1; j <= numStates; j++){
        s1 = i;
        s2 = j;
        System.out.println("S1: " + s1 + ", S2: " + s2);
        Markov.getTransProb(i, j, numStates);
      }
    }

    // ==============================================
    // ============ Q3 ==============================
    // ==============================================

    numStates = 9;
    TS = 3;
    System.out.println("================================");
    System.out.println("Running question 3");
    for (int i = 1; i <= numStates; i++){
      for (int j = 1; j <= numStates; j++){
        s1 = i;
        s2 = j;
        System.out.println("S1: " + s1 + ", S2: " + s2);
        Markov.getSejProb(i, j, numStates, TS);
      }
    }

    // ==============================================
    // ============ Q4 ==============================
    // ==============================================

    numStates = 9;
    TS = 3;
    ssprob[0] = 0.1;        ssprob[1] = 0.1;        ssprob[2] = 0.1;
    ssprob[3] = 0.2;        ssprob[4] = 0.1;        ssprob[5] = 0.2;
    ssprob[6] = 0.05;       ssprob[7] = 0.05;       ssprob[8] = 0.1;
    // if you can figure out how to randomly choose ssprobs then go ahead
    // i dont know how to make them sum to 1 if i pick them randomly
    System.out.println("================================");
    System.out.println("Running question 4");
    for (int i = 1; i <= numStates; i++){
      for (int j = 1; j <= numStates; j++){
        s1 = i;
        s2 = j;
        // System.out.println(Arrays.toString(ssprob));
        System.out.println("S1: " + s1 + ", S2: " + s2);
        Markov.getBiasTransProb(i, j, ssprob);
      }
    }

    // ==============================================
    // ============ Q5 ==============================
    // ==============================================

    numStates = 3;
    TS = 3;
    System.out.println("================================");
    System.out.println("Running question 5");
    for (int i = 1; i <= numStates; i++){
      if (i == 1){
        rates[0] = 10.0;    rates[1] = 5.0;
        rates[2] = 1.0;     rates[3] = 2.0;
        rates[4] = 5.0;     rates[5] = 2.0;
      } else {
        rates[0] = ran.nextDouble() * ran.nextInt(10);
        rates[1] = ran.nextDouble() * ran.nextInt(10);
        rates[2] = ran.nextDouble() * ran.nextInt(10);
        rates[3] = ran.nextDouble() * ran.nextInt(10);
        rates[4] = ran.nextDouble() * ran.nextInt(10);
        rates[5] = ran.nextDouble() * ran.nextInt(10);
      }
      for (int j = 1; j <= numStates; j++){
        s1 = i;
        s2 = j;
        System.out.println(Arrays.toString(rates));
        System.out.println("S1: " + s1 + ", S2: " + s2);
        Markov.getContTransProb(i, j, rates);
      }
    }

    // ==============================================
    // ============ Q6 ==============================
    // ==============================================

    numStates = 3;
    TSC = 0.02;
    System.out.println("================================");
    System.out.println("Running question 6");
    for (int i = 1; i <= numStates; i++){
      if (i == 1){
        rates[0] = 10.0;    rates[1] = 5.0;
        rates[2] = 1.0;     rates[3] = 1.0;
        rates[4] = 2.0;     rates[5] = 3.0;
      } else {
        rates[0] = ran.nextDouble() * ran.nextInt(10);
        rates[1] = ran.nextDouble() * ran.nextInt(10);
        rates[2] = ran.nextDouble() * ran.nextInt(10);
        rates[3] = ran.nextDouble() * ran.nextInt(10);
        rates[4] = ran.nextDouble() * ran.nextInt(10);
        rates[5] = ran.nextDouble() * ran.nextInt(10);
      }
      for (int j = 1; j <= numStates; j++){
        s1 = i;
        s2 = j;
        System.out.println(Arrays.toString(rates));
        System.out.println("S1: " + s1 + ", S2: " + s2);
        Markov.getContSejProb(i, j, rates, TSC);
      }
    }

  } // end of main
}
