package model;

import java.util.Date;

public class UserProfile {

	private class Problem{
		private final String problem;
		private final String awnser;
		private final Date date;
		private final int points;
		private final int timeRequierd;
		private final Operator[] modifiers;
		
		public Problem(String problem, String awnser, int points, int timeRequierd, Operator[] modifiers) {
			this.problem = problem;
			this.awnser = awnser;
			this.date =  new Date();
			this.points = points;
			this.timeRequierd = timeRequierd;
			this.modifiers = modifiers;
		}

		public String getProblem() {
			return problem;
		}
		public String getAwnser() {
			return awnser;
		}
		public Date getDate() {
			return date;
		}
		public int getPoints() {
			return points;
		}
		public int getTimeRequierd() {
			return timeRequierd;
		}
		public Operator[] getModifiers() {
			return modifiers;
		}

	}
}
