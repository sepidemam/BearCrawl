package ch.usi.star.bear;
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

public class InferenceEngine {
	
	private static HashMap<UserClass, InferenceEngine> inferenceMap = new HashMap<UserClass, InferenceEngine>();

	private HashSet<State> states;
	private HashSet<Transition> transitions;
	private HashMap<State, Long> stateOccurrences;
	private State initState;
	private State goalState;

	private String startstate = BearProperties.getInstance().getProperty(BearProperties.STARTSTATE);
	private String endstate = BearProperties.getInstance().getProperty(BearProperties.ENDSTATE);
	
	
	protected static InferenceEngine getInferenceEngine(UserClass userClass) {
		InferenceEngine inferenceEngine;
		if (inferenceMap.containsKey(userClass)) {
			inferenceEngine = inferenceMap.get(userClass);
		} else {
			inferenceEngine = new InferenceEngine();
			inferenceMap.put(userClass, inferenceEngine);
		}
		return inferenceEngine;

	}
	

	public State getGoalState() {
		return goalState;
	}

	public State getInitState() {
		return initState;
	}

	public InferenceEngine() {
		this.states = new HashSet<State>();
		this.transitions = new HashSet<Transition>();
		this.stateOccurrences = new HashMap<State, Long>();
		try {
			this.initState = new State(new Label(this.startstate));
			this.goalState = new State(new Label(this.endstate));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.states.add(initState);
		this.states.add(goalState);
	}

	public int getTransitionOccurrences(Transition transition) {
		for (Transition t : transitions) {
			if (t.equals(transition)) {
				return t.getOccurrences();
			}
		}
		return 0;
	}

	private int signal(State source, State destination) throws Exception {
		if (destination.equals(this.goalState) || source.equals(this.goalState) || destination.equals(this.initState))
			throw new Exception("cannot add transitions to/from the GOAL/START states");
		// insert destination state (it may be new)
		states.add(destination);
		Transition t = this.createOrUpdateTransition(source, destination);
		return t.getOccurrences();
	}

	public int signalTransition(State source, State destination) throws Exception {
		int res = this.signal(source, destination);
		Long occurrences = this.stateOccurrences.get(source);

		if (occurrences == null) {
			occurrences = new Long(1);
		} else
			occurrences++;

		this.stateOccurrences.put(source, occurrences);
		return res;
	}

	private Transition createOrUpdateTransition(State source, State destination) {
		Transition transition = new Transition(source, destination);
		Transition oldT = null;
		Transition newT = null;
		
		for (Transition t : transitions) {
			if (t.equals(transition)) {
				newT = new Transition(t.getSource(), t.getDestination(), t.getOccurrences()+1, t.getProbability());
				oldT = t;
			}
		}
		
		if(newT!=null){
			transitions.remove(oldT);
		}else{
			newT = transition;
		}
		transitions.add(newT);
		return newT;
	}

	@SuppressWarnings("unchecked")
	public Model exportModel(HashMap<String, State> usersState, UserClass uc) throws Exception {
		this.finalizeModel(usersState);
		

		String labelRewardLabel = BearProperties.getInstance().getProperty(BearProperties.LABELREWARDS);
		// this has changed
		RewardSchema rewardSchema = new RewardSchema(labelRewardLabel, states,transitions);
		Model model = new Model((Set<State>) this.states.clone(), (Set<Transition>) this.transitions.clone(), uc, rewardSchema);
		return model;
	}

	private void finalizeModel(HashMap<String, State> usersState) throws Exception {
		this.finalizeTransitions(usersState);
		this.computeProbabilities();
	}


	private void computeProbabilities() throws Exception {
		// saving all outgoing transition for every state
		HashSet<Transition> newTransitions = new HashSet<Transition>();
		for (State s : states) {
			float totalOccurencies = 0;
			Set<Transition> outgoing = new HashSet<Transition>();
			for (Transition t : transitions) {
				if (t.getSource().equals(s)) {
					outgoing.add(t);
					if (t.getOccurrences() <= 0)
						throw new Exception("Fatal error: transition with negative or zero occurrencies");
					totalOccurencies += t.getOccurrences();
				}
			}

			for (Transition t : outgoing) {
				Float probability = ((float) t.getOccurrences()) / totalOccurencies;
				Transition transition = new Transition(t.getSource(), t.getDestination(), t.getOccurrences(), probability);
				newTransitions.add(transition);
			}
		}
		this.transitions = newTransitions;
	}
//
/*	private void computeProbabilities() throws Exception {
		// saving all outgoing transition for every state
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
						throw new Exception("Fatal error: transition with negative or zero occurrencies");
					totalOccurencies += t.getOccurrences();
				}
			}
			i=0;
			setIterator=outgoing.iterator();
			for (Transition t : outgoing) {
				Float reward = (float) (1-(similarity(t.getSource().toString(),t.getDestination().toString())));
				System.out.println(reward);
				System.out.println(t.getSource().toString());
				System.out.println(t.getDestination().toString());
				source=t.getDestination();
				setIterator.next();
				if (source.toString()==t.getSource().toString()){
					System.out.println(t.getSource().toString());
					System.out.println(t.getDestination().toString());
					 Q[i] = (float) (similarity(t.getSource().toString(),t.getDestination().toString()));
					 System.out.println(Q[i]);
				     i++;
				     Arrays.sort(Q);
						float max= Q[Q.length-1];
						 probability=(float) (reward+0.9*max);
						
				}
				else {
				//setIterator.next();
			    probability=(float) (reward);
				}
				
			}
			for (Transition t : outgoing) {
				// probability = probability;
				Transition transition = new Transition(t.getSource(), t.getDestination(), t.getOccurrences(), probability);
				newTransitions.add(transition);
				
			}
		}
		this.transitions = newTransitions;
	}*/
//	
	
	
	private void finalizeTransitions(HashMap<String, State> usersState) {
		this.createOrUpdateTransition(goalState, goalState);
		Set<String> users = usersState.keySet();

		for (String user : users) {
			State lastState = usersState.get(user);
			this.createOrUpdateTransition(lastState, goalState);
		}

		for (State source : states) {
			boolean childLess = true;
			for (State destination : states) {
				// Goal and Initial States are not childless
				if (destination.equals(this.goalState) | destination.equals(this.initState)) {
					continue;
				}
				Transition t = new Transition(source, destination);
				if (transitions.contains(t)) {
					childLess = false;
				}
			}
			// If current state is childless we add a transition to the final
			// state
			if (childLess) {
				createOrUpdateTransition(source, this.goalState);
//				log.debug("State " + source + " is childless, fixing it.");
			}
		}
	}

	public int numberOfStates() {
		return this.states.size();
	}

	public int numberOfTransitions() {
		return transitions.size();
	}

	public boolean containsState(State state) {
		return states.contains(state);
	}

	public boolean containsTransition(Transition transition) {
		return transitions.contains(transition);
	}
//
	/////////////////New Reward Generation Function
	
	private double generateRewards2() {
		//Set<State> states = this.getStates();

		String rewards = "";
		double reward=0;
		/*
		String stringSchema = "\n\nrewards ";
		boolean anyState = false;
		stringSchema += "\"" + this.rewardSchema.getName() + "\"\n\n";
		for (State s : states) {
			if (this.rewardSchema.contains(s)) {
				anyState = true;
				stringSchema += getStateRepresentation(s, false) + " : " + this.rewardSchema.getReward(s) + ";\n";
			}
		}
		
				if (anyState)
			stringSchema += "\nendrewards";
		else
			stringSchema = "";
		rewards += stringSchema;
		
		return rewards;
		*/
	

		for (Transition t : transitions) {

			reward=similarity(t.getSource().toString(),t.getDestination().toString());
	
		
			//System.out.println(t.getDestination());
		}
		return(reward);
		

	
	}	
	
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
