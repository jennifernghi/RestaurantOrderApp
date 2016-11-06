import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Nghi Nguyen
 *
 */
public class FileHandler {
	

	
	/**
	 * reads a menu.txt file, stores each line as a element in map
	 * @return map 
	 */
	public Map<String, Double> fileToMap(){

		String line = "";
		Map<String, Double> map=new HashMap<>();
		BufferedReader reader=null;
		
		InputStream in = getClass().getResourceAsStream("/menu.txt"); 
		try {
			reader = new BufferedReader(new InputStreamReader(in));

			while((line = reader.readLine()) != null){
				line = line.trim();
				int j = line.trim().indexOf('|');
				//get the part before |
				String key = line.substring(0, j).trim();
				//get the part after |
				Double value = Double.valueOf(line.substring(j+1, line.length()).trim());
				map.put(key, value);//put in the map
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		return map;
		
	}
	
	
	
	
	
	
}
