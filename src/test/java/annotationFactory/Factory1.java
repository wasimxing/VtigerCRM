package annotationFactory;

import org.testng.annotations.Factory;

public class Factory1 {
	@Factory
	public Object[] gettestClass()
	
	{
		Object[] obj = new Object[2];
		obj[0]= new Testcase1();
		obj[1]=new TestCase2();
		
		
		return obj;
		
	}

}
