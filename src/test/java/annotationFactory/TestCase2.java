package annotationFactory;

import org.testng.annotations.Test;

public class TestCase2 {
	@Test
	public void factory()
	{
		System.out.println("@Factory ");
	}
	public void sum()
	{
		int a=10;
		int b=18;
		int sum =a+b;
		System.out.println(sum);
	}

	
}
