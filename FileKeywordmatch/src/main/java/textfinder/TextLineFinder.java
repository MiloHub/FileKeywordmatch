package textfinder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class TextLineFinder {

	private static TextLineFinder textLineFinder = null;

	public static void main(String[] args) throws IOException {
		System.out.println("Input file path for source contain keywords..\n");
		Scanner sc = new Scanner(System.in);
		String srcPathForkeyWord = sc.next();
		System.out.println("Input file path for source to find matches..");
		String srcPathToSearchFile = sc.next();
		File f1 = new File(srcPathForkeyWord);
		File f2 = new File(srcPathToSearchFile);
		getTextLineFinderInstance().getAllFileTxtLine(null, f1, f2);

	}

	// prevent obj creation from outside
	private TextLineFinder() {
	}

	/**
	 * 
	 * 
	 * @return instance of TextLineFinder
	 */
	public static TextLineFinder getTextLineFinderInstance() {
		if (textLineFinder == null) {
			textLineFinder = new TextLineFinder();
		}

		return textLineFinder;

	}

	/**
	 * 
	 * This method get the matching word from soruce1 from source 2
	 * 
	 * adding to the map with line numbers.
	 * 
	 * @param searchWord
	 * @param source1
	 * @param source2
	 * @return map of string key and line number in list
	 * @throws IOException
	 */
	Map<String, List<Integer>> getAllFileTxtLine(String searchWord,
			File source1, File source2) throws IOException {
		LineNumberReader linereader = null;
		FileReader reader = null;

		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

		try {

			if (!source1.exists()) {
				System.out.println("File containing Keyword does not exist");
				return null;
			}
			if (!source2.exists()) {
				System.out.println("File to be searched does not exist");
				return null;
			}

			map.clear();
			getKeyWordsInMap(source1, map, searchWord);

			reader = new FileReader(source2);
			linereader = new LineNumberReader(reader);

			String line = "";

			while ((line = linereader.readLine()) != null) {

				// System.out.println("line # " + line + "--"
				// + linereader.getLineNumber());
				for (Entry<String, List<Integer>> entry : map.entrySet()) {
					// System.out.println("Key : " + entry.getKey() +
					// " Value : "
					// + entry.getValue());

					String key = entry.getKey();

					List<Integer> value = entry.getValue();
					String regex = "\\b" + key + "\\b";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(line);

					// System.out.println(tes.matches(regex));

					if (matcher.find()) {
						if (map.get(key) != null) {
							int lineNo = linereader.getLineNumber();
							value.add(lineNo);
							map.put(key, value);
						}
					}
				}
			}
		} finally {
			if (reader != null)
				reader.close();
			if (linereader != null)
				linereader.close();
		}
		printMap(map);
		return map;

	}

	void getKeyWordsInMap(File f, Map<String, List<Integer>> map2,
			String searchWord) throws IOException {
		FileReader reader2 = null;

		LineNumberReader linereader2 = null;

		try {

			reader2 = new FileReader(f);

			linereader2 = new LineNumberReader(reader2);

			String line = "";

			while ((line = linereader2.readLine()) != null) {
				map2.put(line, new ArrayList<Integer>());

			}
			if (map2.get(searchWord) == null && searchWord != null) {

				map2.clear();
				return;
			} else if (map2.get(searchWord) != null && searchWord != null) {
				map2.clear();
				map2.put(searchWord, new ArrayList<Integer>());
			}
		} finally {
			if (reader2 != null)
				reader2.close();
			if (linereader2 != null)
				linereader2.close();
		}

	}

	void printMap(Map<String, List<Integer>> mp) {
		if (mp == null) {
			throw new AssertionError("map is null");
		}
		for (Entry<String, List<Integer>> entry : mp.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());

		}

	}

}
