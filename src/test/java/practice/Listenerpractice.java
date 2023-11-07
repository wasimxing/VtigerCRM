package practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericLib.SampleBase1;
@Listeners(genericLib.ListenerImplementationPractice.class)
public class Listenerpractice extends SampleBase1 {
	@Test
	public void test1()
	{
		System.out.println("Test1");
	}
	//@Test(retryAnalyzer=genericLib.RetryImplementation.class)
	@Test
	public void test2()
	{
		
		System.out.println("Test2");
		Assert.fail();
	}

}
