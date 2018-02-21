package dbengine;

import java.util.ArrayList;

/**
 * @author Nikhil
 *
 */
public class query {
	public void print() {
		
		String qry = "select city,winner,player_match,avg(win_by_wickets),min(win_by_runs) from ipl.csv where season > 2016 and city= 'Bangalore' GROUP by team1 order by win_by_runs";
		String[] words = getWords(qry);
		for(String word : words)
			System.out.println(word);
		
		ArrayList<String> Filename = getFilename(words);
		System.out.println("Filename: "+Filename);
		System.out.println("Base Part: "+getPart(qry,"base"));
		System.out.println("Filter Part: "+getPart(qry, "filter"));
		ArrayList<String> Condition = getCondition(words);
		//System.out.println(Condition.size());
		for(String x : Condition)
			System.out.println(x);
		ArrayList<String> logop = getLogop(words);
		//System.out.println(logop.size());
		for(String x : logop)
			System.out.println(x);
		String[] keys = getSelected(words);
		for(String key : keys)
			System.out.println(key);
		
		System.out.println(getBy(words, "order"));
		System.out.println(getBy(words, "group"));
		
		ArrayList<String> aggFunc = getAggfunc(keys);
		for(String x : aggFunc)
			System.out.println(x);
	}

	public String[] getWords(String qry) {
		String[] words = qry.split(" ");
		return words;
	}
	
	public ArrayList<String> getFilename(String[] words)
	{
		ArrayList<String> Filenames = new ArrayList<String>();
		for(int i=0;i<words.length;i++)
		{
			if(words[i].contains("."))
				Filenames.add(words[i]);
		}
		return Filenames;
	}
	
	public String getPart(String qry, String part)
	{
		String[] parts = qry.split("where");
		if(part == "base")
			return parts[0];
		else
			return parts[1].trim();
	}
	
	public ArrayList<String> getCondition(String[] words)
	{
		//System.out.println(n);
		ArrayList<String> Conditions = new ArrayList<String>();
		for(int i=0;i<words.length;i++)
		{
			if(words[i].equalsIgnoreCase(">") || words[i].equalsIgnoreCase("<") || words[i].equalsIgnoreCase(">=") || words[i].equalsIgnoreCase("<=") || words[i].equalsIgnoreCase("="))
			{
				Conditions.add(words[i-1] +" " + words[i] +" " + words[i+1]);
			}
		}
		//System.out.println(Conditions.size());
		return Conditions;
	}
	
	public ArrayList<String> getLogop(String[] words)
	{
		ArrayList<String> logop = new ArrayList<String>();
		for(int i=0;i<words.length;i++)
		{
			if(words[i].equalsIgnoreCase("and") || words[i].equalsIgnoreCase("or") || words[i].equalsIgnoreCase("not"))
				logop.add(words[i]);
		}
		return logop;
	}
	
	public String[] getSelected(String[] words)
	{
		ArrayList<String> selected = new ArrayList<String>();
		String temp = "";
		for(int i=1;i<words.length;i++)
		{
			if(words[i].equalsIgnoreCase("from"))
				break;
			temp = temp + words[i];
		}
		
		String[] keys = temp.split(",");
		return keys;
	}
	
	public String getBy(String[] words, String key)
	{
		String by = "";
		if(key.equalsIgnoreCase("order")) {
			for(int i=1;i<words.length;i++)
			{
				if(words[i].equalsIgnoreCase("by") && words[i-1].equalsIgnoreCase("order"))
					by = words[i+1];
			}
		
		}
		else {
			for(int i=1;i<words.length;i++)
			{
				if(words[i].equalsIgnoreCase("by") && words[i-1].equalsIgnoreCase("group"))
					by = words[i+1];
			}
		}
		return by;
	}
	
	public ArrayList<String> getAggfunc(String[] keys)
	{
		ArrayList<String> aggFunc = new ArrayList<String>();
		for(int i=0;i<keys.length;i++)
		{
			if(keys[i].startsWith("avg") || keys[i].startsWith("min") || keys[i].startsWith("max") || keys[i].startsWith("count") || keys[i].startsWith("sum"))
				aggFunc.add(keys[i]);
		}
		return aggFunc;
	}
	
}
