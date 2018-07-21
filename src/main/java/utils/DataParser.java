package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dhiraj
 *
 */
public class DataParser {

	public Map<String, Object> elementsMap = new HashMap<>();
	Helper helper = new Helper();

	public void patternAndMatch() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("UIDump.txt"));
			String sCurrent = "";
			String extract = "";
			String finalEntry = "";
			Pattern pattern;
			int i = 0;
			List<MapValueContainer> list = new ArrayList<MapValueContainer>();
			while ((sCurrent = br.readLine()) != null) {
				list.add(MapValueContainer.matchThecontent(new MapValueContainer(), sCurrent));
				if (list.get(i).isResource == true)
					pattern = Pattern.compile("resource-id=\".*?\"");
				else
					pattern = Pattern.compile("class=\".*?\"");
				Matcher matcher = pattern.matcher(sCurrent);
				if (matcher.find()) {
					extract = matcher.group();
					String[] split = extract.split("=");
					finalEntry = split[1].substring(1, split[1].length() - 1);
					// System.out.println(finalEntry + ": " + list[i]);
					if (elementsMap.containsKey(finalEntry) && list.get(i) == elementsMap.get(finalEntry)) {
						list.get(i).count++;
						elementsMap.put(finalEntry, list.get(i));
					} else {
						list.get(i).count = 0;
						elementsMap.put(finalEntry, list.get(i));
					}
				}
				i++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}