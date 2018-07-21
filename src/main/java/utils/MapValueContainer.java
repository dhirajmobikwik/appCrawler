package utils;

/**
 * @author dhiraj
 *
 */
public class MapValueContainer {
	public int count = 0;
	public boolean isClass ;
	public boolean isResource ;
	public boolean isHorizontalScrollable ;
	public boolean isVerticalScrollable ;
	public boolean isClickable ;
	public boolean isEditText ;
	public boolean isPassword ;
	
	public static MapValueContainer matchThecontent(MapValueContainer mvc, String sCurrent) {
		if (sCurrent.contains("clickable=\"true\""))
			mvc.isClickable = true;
		if (sCurrent.contains("scrollable=\"true\"") && sCurrent.contains("HorizontalScrollView"))
			mvc.isVerticalScrollable = true;
		if (sCurrent.contains("scrollable=\"true\"") && sCurrent.contains("widget.ScrollView"))
			mvc.isHorizontalScrollable = true;
		if (sCurrent.contains("password=\"true\""))
			mvc.isPassword = true;
		if (sCurrent.contains("resource-id=\"\"") && sCurrent.contains("class"))
			mvc.isClass = true;
		if (sCurrent.contains("resource-id"))
			mvc.isResource = true;
		return mvc;
	}
}
