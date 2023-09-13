import java.util.*;
import java.lang.Math;

public class CurrentStateCTMC
{
    private int stateNum;
    ArrayList<double[]> transRates;
    ArrayList<double[]> transProbs;
    ArrayList<int[]> correspondingStates;
    double T;
    double[] currentStateTP;
    double[] currentStateTR;
    int[] currentStateCS;

    
    public CurrentStateCTMC(int stateNum, double[] transitions)
    {
    this.stateNum = stateNum;
    transRates = new ArrayList<double[]>();
    transProbs = new ArrayList<double[]>();
    correspondingStates = new ArrayList<int[]>();
    
    double[] transRate1 = new double[2]; 
    double[] transRate2 = new double[2];
    double[] transRate3 = new double[2];
    
    transRate1[0] = transitions[0];
    transRate1[1] = transitions[1];
    transRate2[0] = transitions[2];
    transRate2[1] = transitions[3];
    transRate3[0] = transitions[4];
    transRate3[1] = transitions[5];
    transRates.add(transRate1);
    transRates.add(transRate2);
    transRates.add(transRate3);
    
    int[] corrState1 = {2,3};
    int[] corrState2 = {1,3};
    int[] corrState3 = {1,2};
    correspondingStates.add(corrState1);
    correspondingStates.add(corrState2);
    correspondingStates.add(corrState3);
    
    double[] transProb1 = {(transitions[0]/(transitions[0]+transitions[1])), 
        (transitions[1]/(transitions[0]+transitions[1]))};
    double[] transProb2 = {(transitions[2]/(transitions[2]+transitions[3])), 
        (transitions[3]/(transitions[2]+transitions[3]))};
    double[] transProb3 = {(transitions[4]/(transitions[4]+transitions[5])), 
        (transitions[5]/(transitions[4]+transitions[5]))};
    transProbs.add(transProb1);
    transProbs.add(transProb2);
    transProbs.add(transProb3);
    
    updateArraysOrderOfProb();
    
    T = 0.0;
    }
    
    public double getTransProb(int state) 
    {
        for (int i = 0; i < 2; i++){
            if (currentStateCS[i] == state) {
                return currentStateTP[i];
            }
        }
        return 0.0;
    }
    
    public int towerSamplingCTMC( int state, double time)
    {
        transition(state);
        T = 0.0;
        double delta;
        
        
        while(T <= time) {
            double P = 0.0;
            for (int i =0; i < 2; i ++) {
                P += currentStateTR[i];
            }
            double r = Math.random();

            while (r==0) {
                r = Math.random();
            }

            delta = (-1/P)*Math.log(r);
            
            T += delta;
            
            if (T > time) {
                break;
            }
            
            ArrayList<Double> t = new ArrayList<Double>();
            ArrayList<Integer> possibleTransitions = new ArrayList<Integer>();
            
            t.add(0.0);
            possibleTransitions.add(0);
            
            for (int i = 0; i < 2; i++) {
        
                t.add(currentStateTP[i]);
                possibleTransitions.add(currentStateCS[i]);

            }
    
            for (int i = 0; i < 2; i ++) {
                 t.set((i+1), (t.get(i) + t.get(i+1)));
            }
    
            double r2 = Math.random();
            

    
            for(int i = 1; i < (t.size()); i ++) {
                if (r2 <= t.get(i)) {
                    transition(possibleTransitions.get(i));
                    break;
                }  
            }
        }
        return stateNum;
    }
    
    public void transition(int state)
    {
        if(state > 0 && state <= 3)
        {
            stateNum = state;
            updateArraysOrderOfProb();
        }
    }

    public double getTime(){
        return T;
    }
    
    public void updateArraysOrderOfProb()
    {
        for (int i = 1; i<=3; i++) {
            if (i==stateNum) {
                currentStateTP = transProbs.get(i-1);
                currentStateCS = correspondingStates.get(i-1);
                currentStateTR = transRates.get(i-1);
            }
        }
        
        double tempCurrentStateTP;
        double tempCurrentStateTR;
        int tempCurrentStateCS;
        
        if (currentStateTP[0] < currentStateTP[1])
        {
            tempCurrentStateTP = currentStateTP[0];
            tempCurrentStateTR = currentStateTR[0];
            tempCurrentStateCS = currentStateCS[0];
            
            currentStateTP[0] = currentStateTP[1];
            currentStateTP[1] = tempCurrentStateTP;
            currentStateCS[0] = currentStateCS[1];
            currentStateCS[1] = tempCurrentStateCS;
            currentStateTR[0] = currentStateTR[1];
            currentStateTR[1] = tempCurrentStateTR;
            
        }
        
        if (currentStateTP[0] == currentStateTP[1])
        {
            Random rand = new Random();
            int r3 = rand.nextInt(2);
            if(r3 == 1) {
                tempCurrentStateTP = currentStateTP[0];
                tempCurrentStateTR = currentStateTR[0];
                tempCurrentStateCS = currentStateCS[0];
            
                currentStateTP[0] = currentStateTP[1];
                currentStateTP[1] = tempCurrentStateTP;
                currentStateCS[0] = currentStateCS[1];
                currentStateCS[1] = tempCurrentStateCS;
                currentStateTR[0] = currentStateTR[1];
                currentStateTR[1] = tempCurrentStateTR;

            }
        }
    }
}