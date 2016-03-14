package net.bigpoint;

import static org.junit.Assert.*;
import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;
import net.bigpoint.exceptions.NegativeAmount;

import org.junit.Before;
import org.junit.Test;

public class GasstationImpTest {
	GasstationImp station = null;
	@Before
	public void before() {
		GasPump gpDIESEL = new GasPump(GasType.DIESEL, 10);
		GasPump gpREGULAR = new GasPump(GasType.REGULAR, 10);
		GasPump gpSUPER = new GasPump(GasType.SUPER, 10);
		station = new GasstationImp();
		station.addGasPump(gpDIESEL);
		station.setPrice(GasType.DIESEL, 10);
		station.addGasPump(gpREGULAR);
		station.setPrice(GasType.REGULAR, 10);
		station.addGasPump(gpSUPER);
		station.setPrice(GasType.SUPER, 10);
	}

	 
	
	@Test
	public void test_NumberOfSales() throws NotEnoughGasException,
			GasTooExpensiveException {
		station.buyGas(GasType.DIESEL, 3, 10);
		station.buyGas(GasType.DIESEL, 3, 10);
		station.buyGas(GasType.DIESEL, 3, 10); 
		assertEquals(3, station.getNumberOfSales(), 0);
	}
	
	
	@Test
	public void test_Revenue() throws NotEnoughGasException,
			GasTooExpensiveException {
		station.buyGas(GasType.DIESEL, 3, 10);
		assertEquals(30, station.getRevenue(), 0);
	}

	@Test(expected = NotEnoughGasException.class)
	public void testNotEnoughGasException() throws NotEnoughGasException,
			GasTooExpensiveException {
		station.buyGas(GasType.DIESEL, 100, 10);
	}

	@Test(expected = GasTooExpensiveException.class)
	public void testGasTooExpensiveException() throws NotEnoughGasException,
			GasTooExpensiveException {
		station.buyGas(GasType.DIESEL, 2, 1);
	}
	
	
	
	@Test(expected = NullPointerException.class)
	public void test_Null()  { 
		station = new GasstationImp();
		station.getGasPumps();
	}
	
	
	@Test(expected = NegativeAmount.class)
	public void test_addGasPump_negativeAmount()  { 
		GasPump gpSUPER = new GasPump(GasType.SUPER, -10.5); 
		station.addGasPump(gpSUPER);
	}
	
	
	@Test(expected = NegativeAmount.class)
	public void test_Price_negativeAmount()  { 
		station.setPrice(GasType.SUPER, -9.2);
	}
	

}
