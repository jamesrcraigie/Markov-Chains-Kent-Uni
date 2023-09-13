
import java.lang.Math;
import java.util.*; 
import java.io.PrintWriter;
public class Markov{




    //##################################
    
    
    public static  double getTransProb(int i,int j,int k){
    
        CurrentState currentState = new CurrentState(i, k);
    
        
       return currentState.getTransProb(j);
    }
    
    
    //##################################
    
    
    public static double getSejProb(int s1,int s2,int numStates,double TS){
    
        CurrentState currentState = new CurrentState(s1, numStates);
        double[] hits = new double[numStates + 1];
        for(int i = 0; i < numStates + 1; i++) {
            hits[i] = 0;
        }

        for (int n = 0; n < 1000000; n++) {
            currentState.transition(s1);

            for (int i = 0; i < TS; i++) {
                currentState.towerSampling();
            }
        hits[currentState.getStateNum()]++;
        }
        
    
    return (hits[s2]/1000000.0);
    }
    
    //##################################
    public static double getBiasTransProb(int s1, int s2,double[] ssprob)
    {
        
        CurrentState currentState = new CurrentState(s1, 9);
        currentState.set_ssProbs(ssprob);
        double a = currentState.getTransProb(s2);
    
    
    return a;
    
    }
    
    
    //##################################
    
    
    
    public static double  getContTransProb(int s1,int s2,double[] rates){
    
        CurrentStateCTMC currentStateCTMC = new CurrentStateCTMC(s1, rates);
    
        double a = currentStateCTMC.getTransProb(s2);
    
    
    return a;
    
    
    
    }
    
    
    
    public static double getContSejProb(int s1,int s2,double[] rates,double TSC){
    
        CurrentStateCTMC currentStateCTMC = new CurrentStateCTMC(s1, rates);
        double[] hits = {0, 0, 0, 0};
    
        for(int i = 0; i < 1000000; i++) {
           int a = currentStateCTMC.towerSamplingCTMC(s1, TSC);
           hits[a]++;
        }

    return (hits[s2]/1000000.0);
    }
    
    
    }//end class