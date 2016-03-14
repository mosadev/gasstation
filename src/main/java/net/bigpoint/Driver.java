package net.bigpoint;
 

import java.util.ArrayList;
import java.util.List;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;

public class Driver {

	public static void main(String[] args) { 
		//Initiate the pumps and the prices in the station 
		GasPump gpDIESEL = new GasPump(GasType.DIESEL, 10.9);
		GasPump gpREGULAR = new GasPump(GasType.REGULAR, 10.8);
		GasPump gpSUPER = new GasPump(GasType.SUPER, 10.5);
		GasstationImp station = new GasstationImp();
		station.addGasPump(gpDIESEL);
		station.setPrice(GasType.DIESEL, 3.2);
		station.addGasPump(gpREGULAR);
		station.setPrice(GasType.REGULAR, 4.6);
		station.addGasPump(gpSUPER);
		station.setPrice(GasType.SUPER, 9.2); 
		try {
			//simulate the customers buying the gas
			station.buyGas(GasType.DIESEL, 3, 10);
			station.buyGas(GasType.DIESEL, 3, 10);
			station.buyGas(GasType.REGULAR, 3, 10);
			station.buyGas(GasType.DIESEL, 3, 10);
			station.buyGas(GasType.REGULAR, 3, 10);
			station.buyGas(GasType.SUPER, 3, 10);
			station.buyGas(GasType.SUPER, 3, 10);
			// List<GasPump> list = new ArrayList<GasPump>(station.getGasPumps());
			 
			 
			 
		} catch (NotEnoughGasException e) {
			e.printStackTrace();
		} catch (GasTooExpensiveException e) {
			e.printStackTrace();
		}
		
		//print the result
		System.out.println("NumberOfSales: " + station.getNumberOfSales());
		System.out.println("Revenue: " + station.getRevenue());
		System.out.println("NumberOfCancellationsNoGas: "
				+ station.getNumberOfCancellationsNoGas());
		System.out.println("NumberOfCancellationsTooExpensive: "
				+ station.getNumberOfCancellationsTooExpensive());

	}

}
