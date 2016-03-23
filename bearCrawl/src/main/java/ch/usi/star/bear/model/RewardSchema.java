package ch.usi.star.bear.model;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;

//import org.apache.commons.lang.ArrayUtils;
import ch.usi.star.bear.model.Label;
import ch.usi.star.bear.model.Model;
import ch.usi.star.bear.model.RewardSchema;
import ch.usi.star.bear.model.State;
import ch.usi.star.bear.model.Transition;
import ch.usi.star.bear.model.UserClass;
import ch.usi.star.bear.properties.BearProperties;
import java.util.Collection;
import java.util.HashMap;
import org.apache.log4j.Logger;
//import ch.usi.star.bear.model.Model;
import ch.usi.star.bear.model.State;
import ch.usi.star.bear.model.Transition;

public class RewardSchema {
	//private Logger log = Logger.getLogger(this.getClass().getSimpleName());
	private HashMap<State, Float> schema = new HashMap<State, Float>();
	private String name;
	
	public String getName() {
		return name;
		
		
	}

/*	public RewardSchema(String name, Collection<State> states){
		this.name = name;	
		
		for (State s : states) {
			
			this.schema.put(s, s.getReward());
			
		}
	}*/
	public RewardSchema(String name, Collection<State> states,Collection<Transition> transitions){
		this.name = name;	
	/*	
		for (State s : states) {
			
			this.schema.put(s, s.getReward());
			
		}*/
		
		
		Iterator setIterator = null;
		float probability=0;
		State temp;
		State source;
		float Q [] = null;
		int i=0;
		HashSet<Transition> newTransitions = new HashSet<Transition>();
		for (State s : states) {
			float totalOccurencies = 0;
			Set<Transition> outgoing = new HashSet<Transition>();
			for (Transition t : transitions) {
				if (t.getSource().equals(s)) {
					outgoing.add(t);
					if (t.getOccurrences() <= 0)
						System.out.println("Fatal error: transition with negative or zero occurrencies");
					totalOccurencies += t.getOccurrences();
				}
			}
			i=0;
			setIterator=outgoing.iterator();
			String res1;
			String res2;
			StateMatcher match=new StateMatcher();
			for (Transition t : outgoing) {
				res1= match.find(t.getSource().toString());
				//System.out.println(res1);
				res2= match.find(t.getDestination().toString());
				Float reward = (float) (1-(similarity(res1,res2)));
				//System.out.println(reward);
				System.out.println(t.getSource().toString());
				System.out.println(t.getDestination().toString());
				source=t.getDestination();
				setIterator.next();
				if (source.toString()==t.getSource().toString()){
					System.out.println(t.getSource().toString());
					System.out.println(t.getDestination().toString());
					 Q[i] = (float) (similarity(t.getSource().toString(),t.getDestination().toString()));
					// System.out.println(Q[i]);
				     i++;
				     Arrays.sort(Q);
						float max= Q[Q.length-1];
						 probability=(float) (reward+0.9*max)*100;
						 System.out.println(probability);
						
				}
				else {
				//setIterator.next();
			    probability=(float) (reward)*100;
			    System.out.println(probability);
				}
				this.schema.put(s,  probability);	
			}
			/*for (Transition t : outgoing) {
				// probability = probability;
				Transition transition = new Transition(t.getSource(), t.getDestination(), t.getOccurrences(), probability);
				newTransitions.add(transition);
				
			}*/
		}
	}
	
	
	public float getReward(State s){
		//System.out.println(schema.get(s));
		return schema.get(s);
	}
	
	public boolean contains(State s){
		return schema.containsKey(s);
	}
//----------	
	
	
	///	Similarity Check	
	   public static double similarity(String s1, String s2) {
	        if (s1.length() < s2.length()) { // s1 should always be bigger
	            String swap = s1; s1 = s2; s2 = swap;
	        }
	        int bigLen = s1.length();
	        if (bigLen == 0) { return 1.0; /* both strings are zero length */ }
	        return (bigLen - computeEditDistance(s1, s2)) / (double) bigLen;
	    }

	    public static int computeEditDistance(String s1, String s2) {
	        s1 = s1.toLowerCase();
	        s2 = s2.toLowerCase();

	        int[] costs = new int[s2.length() + 1];
	        for (int i = 0; i <= s1.length(); i++) {
	            int lastValue = i;
	            for (int j = 0; j <= s2.length(); j++) {
	                if (i == 0)
	                    costs[j] = j;
	                else {
	                    if (j > 0) {
	                        int newValue = costs[j - 1];
	                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
	                            newValue = Math.min(Math.min(newValue, lastValue),
	                                    costs[j]) + 1;
	                        costs[j - 1] = lastValue;
	                        lastValue = newValue;
	                    }
	                }
	            }
	            if (i > 0)
	                costs[s2.length()] = lastValue;
	        }
	        return costs[s2.length()];
	    }
	
	
}
