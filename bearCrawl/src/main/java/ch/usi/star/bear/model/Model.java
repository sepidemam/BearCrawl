package ch.usi.star.bear.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import ch.usi.star.bear.model.Model;
import ch.usi.star.bear.properties.BearProperties;
import ch.usi.star.bear.visualization.Edge;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Model {

	private Set<State> states;
	private Set<Transition> transitions;
	private RewardSchema rewardSchema;
	private UserClass userClass;

	public UserClass getUserClass() {
		return userClass;
	}

	public RewardSchema getRewardSchema() {
		return this.rewardSchema;
	}

	public Set<State> getStates() {
		return states;
	}

	public Set<Label> getLabels() {
		HashSet<Label> labels = new HashSet<Label>();
		for (State s : states) {
			labels.addAll(s.getLabels());
			//System.out.println(s.getLabels());
		}
		return labels;
	}

	public Set<Transition> getTransitions() {
		return transitions;
	}

	public Transition getTransitionsFromStateToState(State fromState, State toState) {
		for (Transition t : this.transitions) {
			if (t.getSource().equals(fromState) && t.getDestination().equals(toState)) {
				return t;
			}
		}
		return null;
	}

	public Set<Transition> getTransitionsFromState(State fromState) {
		Set<Transition> result = new HashSet<Transition>();
		for (Transition t : transitions) {
			if (t.getSource().equals(fromState)) {
				result.add(t);
			}
		}
		return result;
	}

	public Model(Set<State> states, Set<Transition> transitions, UserClass uc) {
		this.states = states;
		this.userClass = uc;
		this.transitions = transitions;
	}

	public Model(Set<State> states, Set<Transition> transitions, UserClass uc, RewardSchema schema) {
		this(states, transitions, uc);
		this.rewardSchema = schema;


	/*	for (Transition t : transitions) {

			System.out.println(t.getSource());
			System.out.println(t.getDestination());
		}*/
	}

	public int numberOfStates() {
		return states.size();
	}

	public int numberOfTransitions() {
		return transitions.size();
	}

	public String generatePrismModel(String moduleName) {

		Set<State> states = this.getStates();
		Set<Label> labels = this.getLabels();

		String header = createHeader(moduleName, labels);
		String body = "\n";

		for (State s : states) {
			body += "[] " + "( " + getStateRepresentation(s, false) + " ) -> ";
			Set<Transition> outgoing = this.getTransitionsFromState(s);
			Iterator<Transition> iterator = outgoing.iterator();

			while (iterator.hasNext()) {
				Transition t = iterator.next();
				body += t.getProbability() + " : " + getStateRepresentation(t.getDestination(), true);
				if (iterator.hasNext())
					body += " + ";
				else
					body += ";\n";
			}
		}

		String footer = "\nendmodule";

		String rewards = generateRewards();
		return header + body + footer + rewards;
	}

	private String generateRewards() {
		Set<State> states = this.getStates();

		String rewards = "";
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
		//System.out.println(rewards);
		return rewards;
	}
	/////////////////New Reward Generation Function
	
	private String generateRewards2() {
		Set<State> states = this.getStates();

		String rewards = "";
		double reward=0;
		String stringSchema = "\n\nrewards ";
		boolean anyState = false;
		stringSchema += "\"" + this.rewardSchema.getName() + "\"\n\n";
	/*	for (State s : states) {
			if (this.rewardSchema.contains(s)) {
				anyState = true;
				stringSchema += getStateRepresentation(s, false) + " : " + this.rewardSchema.getReward(s) + ";\n";
			}
		}*/
	


		for (Transition t : transitions) {

			reward=similarity(t.getSource().toString(),t.getDestination().toString());
		
			//System.out.println(t.getDestination());
		}
		
		if (anyState)
			stringSchema += "\nendrewards";
		else
			stringSchema = "";
		rewards += stringSchema;
		
		return rewards;
	
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
	

	//////////////

	private String getStateRepresentation(State s, boolean prime) {
		String rep = "";
		Set<Label> negatedLabels = new HashSet<Label>();
		negatedLabels.addAll(this.getLabels());
		negatedLabels.removeAll(s.getLabels());

		rep += addLabels(s.getLabels(), prime, true);
		rep += " & ";
		rep += addLabels(negatedLabels, prime, false);
		
//System.out.print(prime);
//System.out.print(rep);

		return rep;
	}

	private String addLabels(Set<Label> labels, boolean prime, boolean value) {
		String rep = "";
		Iterator<Label> iterator = labels.iterator();
		while (iterator.hasNext()) {
			rep += "(" + iterator.next();
			if (prime)
				rep += "'";

			if (value) {
				rep += "=true)";
			} else
				rep += "=false)";

			if (iterator.hasNext())
				rep += " & ";
		}
		return rep;
	}

	private String createHeader(String moduleName, Set<Label> labels) {
		String header = "dtmc\n\nmodule " + moduleName + "\n\n";
		for (Label l : labels) {
			if (l.getName() == BearProperties.getInstance().getProperty(BearProperties.STARTSTATE))
				header += l + " : bool init true;\n";
			else
				header += l + " : bool init false;\n";
		}
		return header;
	}
}
