package pages;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;

import utils.DataParser;
import utils.Helper;
import utils.MapValueContainer;

/**
 * @author dhiraj
 *
 */
public class RandomClick extends BaseActivity {

	Helper helper = new Helper();
	DataParser dataParse = new DataParser();
	Map<String, Object> elements;
	Matcher match;

	public void getUIDump() throws IOException {
		//elements = dataParse.elementsMap;
		PrintWriter pw = new PrintWriter(new File("UIDump.txt"));
		helper.sleep(55000);
		String[] s = helper.getDriver().getPageSource().split("<");
		int i = 0;
		String temp;
		while (i < s.length) {
			temp = "<" + s[i];
			if (!temp.contains("</"))
				pw.write(temp + "\n");
			i++;
		}
		pw.close();
	}

	public void readUIAndClick() {
		dataParse.patternAndMatch();
		elements = dataParse.elementsMap;
		elements.forEach((k,
				v) -> System.out.println(k + "\n" + ((MapValueContainer) v).count + ", "
						+ ((MapValueContainer) v).isClass + ", " + ((MapValueContainer) v).isClickable + ", "
						+ ((MapValueContainer) v).isEditText + ", " + ((MapValueContainer) v).isHorizontalScrollable
						+ ", " + ((MapValueContainer) v).isVerticalScrollable + ", "
						+ ((MapValueContainer) v).isPassword + ", " + ((MapValueContainer) v).isResource + "\n\n"));
		for (Map.Entry<String, Object> map : elements.entrySet()) {
			MapValueContainer mapValueContainer = (MapValueContainer) map.getValue();
			String elementToBeClicked = map.getKey();
			if (mapValueContainer.isClass && mapValueContainer.isClickable) {
				if (helper.isElementPresent("class", elementToBeClicked)) {
					helper.findMobileElement("id", elementToBeClicked).click();
					helper.navigateBack(1);
				}
			} else if (mapValueContainer.isResource && mapValueContainer.isClickable) {
				if (helper.isElementPresent("id", elementToBeClicked)) {
					helper.findMobileElement("id", elementToBeClicked).click();
					helper.navigateBack(1);
				}
			}
		}
	}
}
