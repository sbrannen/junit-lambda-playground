
package org.junit.lambda.engine.junit5;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.junit.lambda.core.TestDescriptor;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sam Brannen
 * @since 5.0
 */
@Data
@EqualsAndHashCode
public class JavaTestDescriptor implements TestDescriptor {

	private final Class<?> testClass;

	private final Method testMethod;

	private final Type type;

	private final boolean dynamic;

	private final String id;

	private final String displayName;

	private final TestDescriptor parent;

	private final List<TestDescriptor> children;

	public static JavaTestDescriptor from(String id) {
		// TODO Implement "from TestDescriptor ID"
		//
		// junit5:org.junit.lambda.engine.junit5.JavaTestDescriptorTests#test(java.lang.String, java.math.BigDecimal)

		return null;
	}


	public JavaTestDescriptor(Class<?> testClass, Method testMethod, Type type, boolean dynamic, TestDescriptor parent,
			List<TestDescriptor> children) {

		notNull(testClass, "testClass must not be null");
		notNull(type, "type must not be null");

		this.testClass = testClass;
		this.testMethod = testMethod;
		this.type = type;
		this.dynamic = dynamic;
		this.displayName = (testMethod != null ? testMethod.getName() : testClass.getSimpleName());
		this.parent = parent;
		this.children = (children != null ? Collections.unmodifiableList(children) : Collections.emptyList());
		this.id = String.format("%s:%s#%s(%s)", "junit5", testClass.getName(), testMethod.getName(),
			nullSafeToString(testMethod.getParameterTypes()));
	}

	private static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	private static String nullSafeToString(Class<?>... classes) {
		if (classes == null || classes.length == 0) {
			return "";
		}
		return stream(classes).map(Class::getName).collect(joining(", "));
	}

}
