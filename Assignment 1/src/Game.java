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
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @param g
	 * @param h
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @param m
	 */
	public Game (int a, String b, String c, String d, int e, String f, 
			int g, String h, String i, int j, int k,int l, int m){
		
		this.year = a;
		this.week =b;
		this.day  = c;
		this.home  =d;
		this.homeScore  =e;
		this.away = f ;
		this.awayScore =g ;
		this.date  = h;
		this.result  = i;
	    this.homeYards  = j;
		this.awayYards  = k;
		this.homeTurnovers  = l;
		this.awayTurnovers  =m;
		
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