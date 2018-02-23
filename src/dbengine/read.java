package dbengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class read {
	static String[] header=null;
    int row=0;
    LinkedHashMap<String,ArrayList<Object>> readFile()throws IOException {
	
	LinkedHashMap<String,ArrayList<Object>> map = new LinkedHashMap<>();
	File f=new File("ipl.csv");
	BufferedReader reader = new BufferedReader(new FileReader(f));
    String head=reader.readLine();
    
    header=head.split(",");

    //populate header 
    for(int i=0;i<header.length;i++) {
    	ArrayList<Object> list = new ArrayList<>();
    	map.put(header[i], list);
    }
    //populate with file data
    String entry=reader.readLine();
    while(entry != null) {
    	String temp[]=entry.split(",");
    	for(int i=0;i<header.length;i++) {
    		ArrayList<Object> list= map.get(header[i]);
    	    list.add(temp[i]);
    	    map.put(header[i], list);
    	}
    	entry=reader.readLine();
    }
   
    	
    reader.close();
    row = (map.get(header[0])).size();
    return map;
}
    int getHeader() {
    	return row;
    }

}

/*
 	public void read() {
		
		BufferedReader br=null;
		String line="";

		int flag=0;

		try {
			br=new BufferedReader(new FileReader("ipl.csv"));
			while ((line = br.readLine()) != null) {
				if(flag==0) {
					listHead.addAll(Arrays.asList(line.split(",")));
					flag=1;
				}else {
					String spl[]=line.split(",");
					if(spl.length==18) {
						spl[14]=spl[14]+spl[15];
						spl[15]=spl[16];
						spl[16]=spl[17];
					}
					List<String> li=new ArrayList<String>();
					li.addAll(Arrays.asList(spl));
					if(li.size()==18) {
						li.remove(17);
					}
					list.addAll(li);
				}
			}
		}catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }


		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<list.size();i++) {
			if(Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])").matcher(list.get(i)).matches()) {
				try {
					df.parse(list.get(i));
				}catch(Exception e) {
					System.out.println(e);
				}
			}else if(Pattern.compile("\\d+").matcher(list.get(i)).matches()) {
					Integer.valueOf(list.get(i));
			}
			 else {
			}
		}




	}
*/
