package listener;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericLibraries.SampleBaseClass;
@Listeners(genericLibraries.ListnerImplementation.class)

public class ListenerPracticeTest extends SampleBaseClass{
	@Test
	public void test1()
	{
		System.out.println("test1");
	}

	@Test (retryAnalyzer =genericLibraries.RetryImplementation.class )
	public void test2()
	{
		System.out.println("test2");
		Assert.fail();
	}
	
	
	

}
