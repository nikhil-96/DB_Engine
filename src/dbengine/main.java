package dbengine;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		
		String query = "select city,winner,player_match,avg(win_by_wickets),min(win_by_runs) from ipl.csv where season > 2016 and city= 'Bangalore' GROUP by team1 order by win_by_runs";
		QueryParameter queryparameter = new QueryParameter(query);
		ArrayList<String> conditions = queryparameter.getConditions();
		for(String x : conditions)
			System.out.println(x);
	}

}
