/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.lambda.core;

public final class Assert {

	private Assert() {
	}

	public static void assertTrue(String message, boolean condition) {
		if (!condition) {
			fail(message);
		}
	}

	public static void assertTrue(boolean condition) {
		assertTrue(null, condition);
	}

	public static void assertFalse(String message, boolean condition) {
		assertTrue(message, !condition);
	}

	public static void assertFalse(boolean condition) {
		assertFalse(null, condition);
	}

	public static void fail(String message) {
		if (message == null) {
			throw new AssertionError();
		}
		throw new AssertionError(message);
	}

	public static void assertEquals(String message, Object expected, Object actual) {
		if (equalsRegardingNull(expected, actual)) {
			return;
		}
		else {
			failNotEquals(message, expected, actual);
		}
	}

	private static boolean equalsRegardingNull(Object expected, Object actual) {
		if (expected == null) {
			return actual == null;
		}

		return isEquals(expected, actual);
	}

	private static boolean isEquals(Object expected, Object actual) {
		return expected.equals(actual);
	}

	public static void assertEquals(Object expected, Object actual) {
		assertEquals(null, expected, actual);
	}

	public static void assertNotEquals(String message, Object unexpected, Object actual) {
		if (equalsRegardingNull(unexpected, actual)) {
			failEquals(message, actual);
		}
	}

	public static void assertNotEquals(Object unexpected, Object actual) {
		assertNotEquals(null, unexpected, actual);
	}

	private static void failEquals(String message, Object actual) {
		String formatted = "Values should be different. ";
		if (message != null) {
			formatted = message + ". ";
		}

		formatted += "Actual: " + actual;
		fail(formatted);
	}

	public static void assertEquals(long expected, long actual) {
		assertEquals(null, expected, actual);
	}

	public static void assertEquals(String message, long expected, long actual) {
		if (expected != actual) {
			failNotEquals(message, Long.valueOf(expected), Long.valueOf(actual));
		}
	}

	public static void assertNotNull(String message, Object object) {
		assertTrue(message, object != null);
	}

	public static void assertNotNull(Object object) {
		assertNotNull(null, object);
	}

	public static void assertNull(String message, Object object) {
		if (object == null) {
			return;
		}
		failNotNull(message, object);
	}

	public static void assertNull(Object object) {
		assertNull(null, object);
	}

	private static void failNotNull(String message, Object actual) {
		String formatted = "";
		if (message != null) {
			formatted = message + " ";
		}
		fail(formatted + "expected null, but was:<" + actual + ">");
	}

	public static void assertSame(String message, Object expected, Object actual) {
		if (expected == actual) {
			return;
		}
		failNotSame(message, expected, actual);
	}

	public static void assertSame(Object expected, Object actual) {
		assertSame(null, expected, actual);
	}

	public static void assertNotSame(String message, Object unexpected, Object actual) {
		if (unexpected == actual) {
			failSame(message);
		}
	}

	public static void assertNotSame(Object unexpected, Object actual) {
		assertNotSame(null, unexpected, actual);
	}

	private static void failSame(String message) {
		String formatted = "";
		if (message != null) {
			formatted = message + " ";
		}
		fail(formatted + "expected not same");
	}

	private static void failNotSame(String message, Object expected, Object actual) {
		String formatted = "";
		if (message != null) {
			formatted = message + " ";
		}
		fail(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
	}

	private static void failNotEquals(String message, Object expected, Object actual) {
		fail(format(message, expected, actual));
	}

	private static String format(String message, Object expected, Object actual) {
		String formatted = "";
		if (message != null && !message.equals("")) {
			formatted = message + " ";
		}
		String expectedString = String.valueOf(expected);
		String actualString = String.valueOf(actual);
		if (expectedString.equals(actualString)) {
			return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: "
					+ formatClassAndValue(actual, actualString);
		}
		else {
			return formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
		}
	}

	private static String formatClassAndValue(Object value, String valueString) {
		String className = value == null ? "null" : value.getClass().getName();
		return className + "<" + valueString + ">";
	}

	public static void assertThrows(Class<? extends Throwable> expectedThrowable, Executable executable) {
		expectThrows(expectedThrowable, executable);
	}

	public static <T extends Throwable> T expectThrows(Class<T> expectedThrowable, Executable executable) {
		try {
			executable.execute();
		}
		catch (Throwable actualThrown) {
			if (expectedThrowable.isInstance(actualThrown)) {
				@SuppressWarnings("unchecked")
				T retVal = (T) actualThrown;
				return retVal;
			}
			else {
				String mismatchMessage = format("unexpected exception type thrown;", expectedThrowable.getSimpleName(),
					actualThrown.getClass().getSimpleName());
				throw new AssertionError(mismatchMessage, actualThrown);
			}
		}
		String message = String.format("expected %s to be thrown, but nothing was thrown",
			expectedThrowable.getSimpleName());
		throw new AssertionError(message);
	}

	public interface Executable {
		void execute() throws Throwable;
	}

}
