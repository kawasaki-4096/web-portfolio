package com.studio4096.util;

public class NamingUtils {
	/**
	 * アンダースコア区切りの文字列をキャメルケースに変換します。
	 * <ul>
	 * <li>ANY_TABLE_NAME ⇒ anyTableName</li>
	 * <li>any_column_name ⇒ anyColumnName</li>
	 * </ul>
	 * 
	 * @param s
	 *            アンダースコア区切りの文字列
	 * @param isClassName
	 *            trueを設定すると先頭文字列が大文字になります。
	 * @return キャメルケース文字列
	 */
	public static String toCamelCase(String s, boolean isClassName) {
		String[] parts = s.split("_");
		String camelCaseString = "";
		int i = 0;
		for (String part : parts) {
			if (isClassName || i > 0) {
				camelCaseString = camelCaseString + toProperCase(part);
			} else {
				camelCaseString = camelCaseString + part.toLowerCase();
			}
			i++;
		}
		return camelCaseString;
	}

	private static String toProperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	public static String toUnderScoreName(final String name) {
		StringBuilder sb = new StringBuilder();
		// 小文字と大文字の間で分割
		String[] strs = name.split("(?<=[a-z])(?=[A-Z])");
		for (String s : strs) {
			sb.append(s + "_");
		}
		String result = sb.toString().toUpperCase();
		return result.substring(0, result.lastIndexOf("_"));
	}

	public static String toLowerCaseTopChar(String str) {
		if (str != null && str.length() > 1) {
			String body = str.substring(1);
			String top = String.valueOf(str.charAt(0)).toLowerCase();
			return top + body;
		}
		return str.toLowerCase();
	}
	protected static String toUpperCaseTopChar(String str) {
		if (str != null && str.length() > 1) {
			String body = str.substring(1);
			String top = String.valueOf(str.charAt(0)).toUpperCase();
			return top + body;
		}
		return str.toLowerCase();
	}
}
