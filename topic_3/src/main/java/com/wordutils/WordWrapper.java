package com.wordutils;

public class WordWrapper {

	public String wrap(final String str, int wrapLength) {
		String newLineStr = System.getProperty("line.separator");
		if (str == null) {
			return null;
		}
		if (wrapLength < 1) {
			wrapLength = 1;
		}
		final int inputLineLength = str.length();
		int offset = 0;
		final StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);

		while (offset < inputLineLength) {
			if (str.charAt(offset) == ' ') {
				offset++;
				continue;
			}
			// only last line without leading spaces is left
			if (inputLineLength - offset <= wrapLength) {
				break;
			}
			int spaceToWrapAt = str.lastIndexOf(' ', wrapLength + offset);

			if (spaceToWrapAt >= offset) {
				// normal case
				wrappedLine.append(str.substring(offset, spaceToWrapAt));
				wrappedLine.append(newLineStr);
				offset = spaceToWrapAt + 1;

			} else {
				// really long word or URL
				// wrap really long word one line at a time
				wrappedLine.append(str.substring(offset, wrapLength + offset));
				wrappedLine.append(newLineStr);
				offset += wrapLength;
			}
		}
		// Whatever is left in line is short enough to just pass through
		wrappedLine.append(str.substring(offset));
		return wrappedLine.toString();
	}
}
