package com.heb.groceries;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	com.heb.groceries.AllUnitTests.class,
	com.heb.groceries.AllFunctionalTests.class
})
public class AllTests {

}
