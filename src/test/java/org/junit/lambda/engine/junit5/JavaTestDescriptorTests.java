
package org.junit.lambda.engine.junit5;

import static org.junit.lambda.core.Assert.assertEquals;
import static org.junit.lambda.core.TestDescriptor.Type.ROOT;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * Unit tests for {@link JavaTestDescriptor}.
 *
 * @author Sam Brannen
 * @since 5.0
 */
public class JavaTestDescriptorTests {

	public static void main(String... args) throws Exception {

		Class<JavaTestDescriptorTests> testClass = JavaTestDescriptorTests.class;
		Method testMethod = testClass.getDeclaredMethod("test");
		JavaTestDescriptor descriptor = new JavaTestDescriptor(testClass, testMethod, ROOT, false, null, null);

		System.err.println(descriptor);
		System.err.println(descriptor.getId());

		testMethod = testClass.getDeclaredMethod("test", String.class, BigDecimal.class);
		descriptor = new JavaTestDescriptor(testClass, testMethod, ROOT, false, null, null);

		System.err.println(descriptor);
		System.err.println(descriptor.getId());

		assertEquals(
			"junit5:org.junit.lambda.engine.junit5.JavaTestDescriptorTests#test(java.lang.String, java.math.BigDecimal)",
			descriptor.getId());
	}

	void test() {
	}

	void test(String txt, BigDecimal sum) {
	}

}
