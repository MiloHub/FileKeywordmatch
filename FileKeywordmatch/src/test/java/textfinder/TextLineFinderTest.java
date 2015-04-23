package textfinder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TextLineFinderTest {

	TextLineFinder textLineFinder = null;

	String searchString;

	File source1;

	File source2;

	@Before
	public void setup() {
		String srcPathForkeyWord = null;
		String srcPathToSearchFile = null;
		searchString = "dhanraj";
		// String srcPathForkeyWord = "C:/dhanaraj/test1.txt";
		URL url = this.getClass().getResource("test1.txt");
		//System.out.println(url.getPath());
		srcPathForkeyWord = url.getPath();
		// srcPathToSearchFile = "C:/dhanaraj/test.txt";
		url = null;
		url = this.getClass().getResource("test.txt");
		//System.out.println(url.getPath());
		srcPathToSearchFile = url.getPath();
		source1 = new File(srcPathForkeyWord);
		source2 = new File(srcPathToSearchFile);

		textLineFinder = TextLineFinder.getTextLineFinderInstance();
	}

	@Test
	public void testGetAllFileTxtLine() throws IOException {
		
		System.out.println("*************************************");
		
		System.out.println("***********test case 1 start*********");

		Map<String, List<Integer>> map = textLineFinder.getAllFileTxtLine(
				"dhanaraj", source1, source2);

		Assert.assertNotNull("map is null.." + map);

		Assert.assertEquals("Search word not found...", 1,
				((List<Integer>) map.get("dhanaraj")).get(0).intValue());
		map.clear();
		map = textLineFinder.getAllFileTxtLine(
				null, source1, source2);

		Assert.assertNotNull("map is null.." + map);
		for (Entry<String, List<Integer>> entry : map.entrySet()) {
			//System.out.println(entry.getKey() + " = " + entry.getValue());
			
		}
		System.out.println("*************************************");
		
		System.out.println("***********test case 1 end*********");

	}
	
	@Test
	public void get(){
		String tes = "this is vijayan";
				String t = "vijayan";
				String regex = "\\b"+t+"\\b";
		         Pattern pattern = Pattern.compile(regex);
		         Matcher matcher = pattern.matcher(tes);
		         
		         //System.out.println(tes.matches(regex));
		         
		         if(matcher.find()){
		        	//System.out.println(matcher.group());
		         }

		
		
		//System.exit(0);
	}

	@Test
	public void getKeyWordsInMap() throws IOException {
		System.out.println("*************************************");
		
		System.out.println("***********test case 2 start*********");
		
		HashMap<String, List<Integer>> mp = new HashMap<String, List<Integer>>();
		List<Integer> lineList = new ArrayList<Integer>();
		lineList.add(1);
		lineList.add(2);
		mp.put("test", lineList);
		textLineFinder.getKeyWordsInMap(source1, mp, "dhanaraj");
		Assert.assertEquals("Search word list", 1, mp.size());
		textLineFinder.getKeyWordsInMap(source1, mp, null);
		Assert.assertEquals("Search word list", 4, mp.size());
		System.out.println("*************************************");
		
		System.out.println("***********test case 2 ends*********");


	}

	@Test
	public void testPrintMap() {
		System.out.println("*************************************");
		
		System.out.println("***********test case 3 start*********");

		HashMap<String, List<Integer>> mp = new HashMap<String, List<Integer>>();
		List<Integer> lineList = new ArrayList<Integer>();
		lineList.add(1);
		lineList.add(2);
		mp.put("test", lineList);
		textLineFinder.printMap(mp);
		System.out.println("*************************************");
		
		System.out.println("***********test case 3 ends*********");
	}

	@After
	public void tearDown() {
		textLineFinder = null;

	}

}
