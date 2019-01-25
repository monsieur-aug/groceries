package com.heb.groceries;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	com.heb.groceries.model.ProductTest.class,
	com.heb.groceries.model.ProductBuilderTest.class
})
public class AllUnitTests {

}
