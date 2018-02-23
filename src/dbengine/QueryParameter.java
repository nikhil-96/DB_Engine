package dbengine;

import java.util.ArrayList;

/**
 * @author Nikhil
 *
 */
public class QueryParameter {
	
	private String query = null;
	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> filenames = new ArrayList<String>();
	private String basePart = null;
	private String filterPart = null;
	private ArrayList<String> conditions = new ArrayList<String>();
	private ArrayList<String> operators = new ArrayList<String>();
	private ArrayList<String> selected = new ArrayList<String>();
	private String orderby = null;
	private String groupby = null;
	private ArrayList<String> aggregates = new ArrayList<String>();
	
	//Parameterized Constructor
	public QueryParameter(String query) {
		
		this.query = query;
		setWords();
		setFilenames();
		setBase();
		setFilter();
		setConditions();
		setOperators();
		setSelected();
		setOrder();
		setGroup();
		setAggregates();
	}
	
	public ArrayList<String> getWords()
	{
		return words;
	}
	
	public void setWords()
	{
		String[] words = query.split(" ");
		for(String word : words)
			this.words.add(word);
	}
	
	public ArrayList<String> getFilenames()
	{
		return filenames;
	}
	
	public void setFilenames()
	{
		String[] words = query.split(" ");
		for(int i=0;i<words.length;i++)
		{
			if(words[i].contains("."))
				this.filenames.add(words[i]);
		}
	}
	
	public String getBase()
	{
		return basePart;
	}
	
	public void setBase()
	{
		String[] parts = query.split("where");
		this.basePart = parts[0];
	}
	
	public String getFilter()
	{
		return filterPart;
	}
	
	public void setFilter()
	{
		String[] parts = query.split("where");
		this.filterPart = parts[1];
	}
	
	public ArrayList<String> getConditions()
	{
		return conditions;
	}
	
	public void setConditions()
	{
		String[] words = query.split(" ");
		for(int i=0;i<words.length;i++)
		{
			if(words[i].equalsIgnoreCase(">") || words[i].equalsIgnoreCase("<") || words[i].equalsIgnoreCase(">=") || words[i].equalsIgnoreCase("<=") || words[i].equalsIgnoreCase("="))
			{
				this.conditions.add(words[i-1] +" " + words[i] +" " + words[i+1]);
			}
		}
	}
	
	public ArrayList<String> getOperators()
	{
		return operators;
	}
	
	public void setOperators()
	{
		String[] words = query.split(" ");
		for(int i=0;i<words.length;i++)
		{
			if(words[i].equalsIgnoreCase("and") || words[i].equalsIgnoreCase("or") || words[i].equalsIgnoreCase("not"))
				this.operators.add(words[i]);
		}
	}
	
	public ArrayList<String> getSelected()
	{
		return selected;
	}
	
	public void setSelected()
	{
		String[] words = query.split(" ");
		String temp = "";
		for(int i=1;i<words.length;i++)
		{
			if(words[i].equalsIgnoreCase("from"))
				break;
			temp = temp + words[i];
		}
		
		String[] keys = temp.split(",");
		for(String key : keys)
			this.selected.add(key);
	}
	
	public String getOrder()
	{
		return orderby;
	}
	
	public void setOrder()
	{
		String[] words = query.split(" ");
		for(int i=1;i<words.length;i++)
		{
			if(words[i].equalsIgnoreCase("by") && words[i-1].equalsIgnoreCase("order"))
				this.orderby = words[i+1];
		}
	}
	
	public String getGroup()
	{
		return groupby;
	}
	
	public void setGroup()
	{
		String[] words = query.split(" ");
		for(int i=1;i<words.length;i++)
		{
			if(words[i].equalsIgnoreCase("by") && words[i-1].equalsIgnoreCase("group"))
				this.groupby = words[i+1];
		}
	}
	
	public ArrayList<String> getAggregates()
	{
		return aggregates;
	}
	
	public void setAggregates()
	{
		String[] words = query.split(" ");
		String temp = "";
		for(int i=1;i<words.length;i++)
		{
			if(words[i].equalsIgnoreCase("from"))
				break;
			temp = temp + words[i];
		}
		
		String[] keys = temp.split(",");
		for(int i=0;i<keys.length;i++)
		{
			if(keys[i].startsWith("avg") || keys[i].startsWith("min") || keys[i].startsWith("max") || keys[i].startsWith("count") || keys[i].startsWith("sum"))
				this.aggregates.add(keys[i]);
		}
	}

}
