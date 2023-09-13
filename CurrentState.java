import java.util.*;
import java.lang.Math;
public class CurrentState
{
    
    private int stateNum;
    private int totalStates;
    private int xCoOrd;
    private int yCoOrd;
    private int xyMax;
    private double transProbSelf;
    private double ssProb;
    private int northState;
    private int southState;
    private int eastState;
    private int westState;
    private int stepCount;
    private double[] ssProbs; 


    
    public CurrentState(int stateNum, int totalStates)
    {
        
        this.stateNum = stateNum;
        this.totalStates = totalStates;
        ssProbs = new double[totalStates];
        for (int i = 0; i < totalStates; i ++)
        {
            ssProbs[i] = (1.0/totalStates);
        }
        update_ssProb();
        xyMax = (int)Math.sqrt(totalStates);
        setCoOrds(stateNum);
        setAdjStates();
        updateTransProbSelf();
        stepCount=0;
    }

    public double getTransProb(int s2) 
{
    if (stateNum == s2) {
        return transProbSelf;
    }
    double accProb = getAccProb(s2);
    return accProb*0.25;
      
}

public double getAccProb(int s2) 
{
    if (s2 == northState || s2 == southState || s2 == eastState || s2 == westState) {
        return Math.min(1.0,(getEqualssProb(s2)/ssProb));
    }
    else if (s2 == stateNum) {
        if (transProbSelf > 0)
        {
            return Math.min(1.0,(getEqualssProb(s2)/ssProb));
        }
        else
        {
            return 0.0;
        }
    }
    else{
        return 0.0;
    }
}

public int getStateNum(int x, int y)
{
    int state = 0;
    for (int i = y; i > 0; i--) {
        for (int n = x ; n > 0; n--) {
            state++;
        }
        x = xyMax;
    }
    return state;
}

public void setCoOrds(int state)
{
    this.xCoOrd = 0;
    this.yCoOrd = 1;
     for (int i = 0; i < state; i++) {
        xCoOrd++;
        if (xCoOrd > xyMax) {
            xCoOrd = 1;
            yCoOrd++;
        }
}
}

public void transition(int num)
{
    if (0 < num && num <= totalStates)
    {
        stateNum = num;
        setCoOrds(stateNum);
        setAdjStates();
        update_ssProb();
        updateTransProbSelf();
        stepCount ++;
    }
}

public void setAdjStates()
{
    if (yCoOrd == xyMax){ 
        northState = 0;
    }
    else {
        northState = getStateNum(xCoOrd, yCoOrd +1);
    }

    if (yCoOrd == 1) {
        southState = 0;
    }
    else {
        southState = getStateNum(xCoOrd, yCoOrd -1);
    }

    if (xCoOrd == xyMax) {
        eastState = 0;
    }
    else {
        eastState = getStateNum(xCoOrd +1, yCoOrd);
    }

    if (xCoOrd == 1) {
        westState = 0;
    }
    else {
        westState = getStateNum(xCoOrd -1, yCoOrd);
    }
}

public double getEqualssProb(int s)
{
    if (s > 0 && s <= totalStates) {
        return ssProbs[s-1];
    }
    else {
        return 0.0;
    }
}

public void updateTransProbSelf()
{
    transProbSelf = (1.0 - getTransProb(northState) - getTransProb(southState) 
    -  getTransProb(eastState) - getTransProb(westState));
}

public void towerSampling()
{
    double[] ssprobs = {transProbSelf, getTransProb(northState), 
        getTransProb(southState), getTransProb(eastState), getTransProb(westState)};
    int[] states = {stateNum, northState, southState, eastState, westState}; 
    
    double temp;
    int temp2;
    
    for (int i = 0; i < 5; i++) {
        for (int j = 1; j < 4; j++) {
            if (ssprobs[j-1] < ssprobs[j]) {
                temp = ssprobs[j-1];
                temp2 =states[j-1];
                
                ssprobs[j-1] = ssprobs[j];
                states[j-1] = states[j];
                
                ssprobs[j] = temp;
                states[j] = temp2;
            }
        }
        
    }
    
    ArrayList<Double> t = new ArrayList<Double>();
    ArrayList<Integer> possibleTransitions = new ArrayList<Integer>();
    t.add(0.0);
    possibleTransitions.add(0);
    for (int i = 0; i < 5; i++) {
        if(ssprobs[i] != 0) {
            t.add(ssprobs[i]);
            possibleTransitions.add(states[i]);
        }
    }
    
    for (int i = 0; i < (t.size()-1); i ++) {
        t.set((i+1), (t.get(i) + t.get(i+1)));
    }
    
    double r = Math.random();
    
    
    for(int i = 1; i < (t.size()); i ++) {
        if (r <= t.get(i)) {
            transition(possibleTransitions.get(i));
            break;
        }
    }
}

public int getStepcount()
{
    return stepCount;
}

public int getStateNum()
{
    return stateNum;
}

public void update_ssProb()
{
    ssProb = getEqualssProb(stateNum);
}

public int set_ssProbs(double[] newProbs)
{
    if (!(newProbs.length == totalStates))
    {
        return 0;
    }
    for(int i = 0; i < totalStates; i++)
    {
        ssProbs[i] = newProbs[i];
    }
    update_ssProb();
    return 1;
}
}