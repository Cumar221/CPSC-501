/**
 * Comp 2631
 * Assignment 1
 * Instructor: Jason Heard
 * Due Date: Wed,Sept 24, 2014 
 * @author Cumar Yusuf
 */
public class Game {
	private int year;
	private String week;
	private String day;
	private String home;
	private int homeScore;
	private String away;
	private int awayScore;
	private String date;
	private String result;
	private int homeYards;
	private int awayYards;
	private int homeTurnovers;
	private int awayTurnovers;
	
	
	/**
	 * This method sets the current fields to store actual games from input file
	 * @param newYear
	 * @param newWeek
	 * @param newDay
	 * @param newHome
	 * @param newHomeScore
	 * @param newAway
	 * @param newAwayScore
	 * @param newDate
	 * @param newResult
	 * @param newHomeYards
	 * @param newAwayYards
	 * @param newHomeTurnover
	 * @param newAwayTurnover
	 */
	public Game (int newYear, String newWeek, String newDay, 
			String newHome, int newHomeScore, String newAway, 
			int newAwayScore, String newDate, String newResult, 
			int newHomeYards, int newAwayYards,int newHomeTurnover, 
			int newAwayTurnover){
		
		this.year = newYear;
		this.week =newWeek;
		this.day  = newDay;
		this.home  =newHome;
		this.homeScore  =newHomeScore;
		this.away = newAway ;
		this.awayScore =newAwayScore ;
		this.date  = newDate;
		this.result  = newResult;
	    this.homeYards  = newHomeYards;
		this.awayYards  = newAwayYards;
		this.homeTurnovers  = newHomeTurnover;
		this.awayTurnovers  =newAwayTurnover;
		
	}	
	/**
 	* This method just returns the year of the game.
 	* @return
 	*/
	public int getYear(){
		return year;
	}
	/**
 	* This method just returns the week of the game.
 	* @return
 	*/
	public String getWeek(){
		return week;
	}
	/**
	 * This method just returns the day of the game.
	 * @return
	 */
	public String getDay(){
		return day;
	}
	/**
	 * This method just returns the home team of the game.
	 * @return
	 */
	public String getHome(){
		return home;
	}
	/**
	 * This method just returns the home team score of the game.
	 * @return
	 */
	public int getHomeScore(){
		return homeScore;
	}
	/**
	 * This method just returns the  away team of the game.
	 * @return
	 */
	public String getAway(){
		return away;
	}
	/**
	 * This method just returns the away team score of the game.
	 * @return
	 */
	public int getAwayScore(){
		return awayScore;
	}
	/**
	 * This method just returns the date of the game.
	 * @return
	 */
	public String getDate(){
		return date;
	}
	/**
	 * This method just returns the result of the game.
	 * @return
	 */
	public String getResult(){
		return result;
	}
	/**
	 * This method just returns the home team's yards of the game.
	 * @return
	 */
	public int getHomeYards(){
		return homeYards;
	}
	/**
	 * This method just returns the away team's yards of the game.
	 * @return
	 */
	public int getAwayYards(){
		return awayYards;
	}
	/**
	 * This method just returns the home team's turnovers of the game.
	 * @return
	 */
	public int getHomeTurnovers(){
		return homeTurnovers;
	}
	/**
	 * This method just returns the away team's turnovers of the game.
	 * @return
	 */
	public int getAwayTurnovers(){
		return awayTurnovers;
	}
}