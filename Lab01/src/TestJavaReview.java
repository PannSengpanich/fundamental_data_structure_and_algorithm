import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class TestJavaReview {

	@Test
	public void testNewPoint() {
		Point2D p1=new Point2D(2.3,4.5);
		assertTrue(p1.getX()==2.3);
		assertTrue(p1.getY()==4.5);
	}

	@Test
	public void testFindDistance() {
		Point2D p1=new Point2D(2.3,4.5);
		Point2D p2=new Point2D(1.3,2.6);
		System.out.println(p1.distance(p2));
		assertTrue(Math.abs(p1.distance(p2)-2.1470910553583886) < 10e-6);
		p2.setX(0.0);
		p2.setY(0.0);
		assertTrue(Math.abs(p1.distance(p2)-5.0537115074) < 10e-6);
	}
	

}
