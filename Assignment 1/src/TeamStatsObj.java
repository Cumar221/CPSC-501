
public class TeamStatsObj {
	private int wins;
	private int losses;
	private int ties;
	private double  pointPercentage;
	private int points;
	
	public TeamStatsObj(int newW, int newL, int newT, double newPP, int newP) {
		this.wins = newW;
		this.losses = newL;
		this.ties = newT;
		this.pointPercentage = newPP;
		this.points = newP;
	}
	public int getWins(){
		return wins; 
	}
	public void setWins(int newWins){
		this.wins = newWins;
	}
	public int getLosses(){
		return losses;
	}
	public void setLosses(int newLosses){
		this.losses = newLosses;
	}
	public int getTies(){
		return ties;
	}
	public void setTies(int newTies){
		this.ties = newTies;
	}
	public double getPointPercentage(){
		return pointPercentage;
	}
	public void setPointPercentage(double newPP){
		this.pointPercentage = newPP;
	}
	public int getPoints(){
		return points;
	}
	public void setPoints(int newPoints){
		this.points = newPoints;
	}
}
