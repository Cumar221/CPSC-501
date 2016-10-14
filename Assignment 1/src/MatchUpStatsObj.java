
public class MatchUpStatsObj {

	private int total = 0;
	private int wins = 0;
	private int wins2 = 0;
	private int points = 0;
	private int points2 = 0;
	
	public MatchUpStatsObj(int newTotal, int newWins, int newWins2, int newPoints, int newPoints2){
		this.total = newTotal;
		this.wins = newWins;
		this.wins2 = newWins2;
		this.points = newPoints;
		this.points2 = newPoints2;
	}
	public int getTotal(){
		return total;
	}
	public int getWins(){
		return wins;
	}
	public int getWins2(){
		return wins2;
	}
	public int getPoints(){
		return points;
	}
	public int getPoints2(){
		return points2;
	}
}
