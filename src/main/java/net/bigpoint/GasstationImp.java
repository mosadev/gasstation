package net.bigpoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable; 

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;
import net.bigpoint.exceptions.NegativeAmount;

public class GasstationImp implements GasStation {

	private Hashtable<GasType,GasPump> pumps = null; 
	Hashtable<GasType, Double>  gasPrices = new Hashtable<GasType, Double>();
	private double revenue = 0;
	private int numberOfSales = 0;
	private int numberOfCancellationsNoGas = 0;
	private int numberOfCancellationsTooExpensive = 0;
	
	public void addGasPump(GasPump pump) throws NegativeAmount {
		
		if(pump.getRemainingAmount() < 0){
			throw new NegativeAmount();
		}
		if (pumps == null) {
			pumps = new Hashtable<GasType, GasPump>();
		}
		pumps.put(pump.getGasType(), pump);
	}

	public Collection<GasPump> getGasPumps() {   
		return pumps.values() ;
	}

	public double buyGas(GasType type, double amountInLiters,
			double maxPricePerLiter) throws NotEnoughGasException,
			GasTooExpensiveException { 
		//transaction cancelled - price is expensive
		if(maxPricePerLiter < getPrice(type)){ 
			numberOfCancellationsTooExpensive++;
			throw new GasTooExpensiveException();
		} 
		//transaction cancelled - no enough gas in the pump
		if(amountInLiters > pumps.get(type).getRemainingAmount()){ 
			numberOfCancellationsNoGas++;
			throw new NotEnoughGasException();
		} 
		//customer pay for the gas
		pumps.get(type).pumpGas(amountInLiters); 
		revenue+= amountInLiters * getPrice(type);
		numberOfSales++;
		return 0;
	}

	public double getRevenue() {
		return revenue;
	}

	public int getNumberOfSales() {
		return numberOfSales;
	}

	public int getNumberOfCancellationsNoGas() {
		return numberOfCancellationsNoGas;
	}

	public int getNumberOfCancellationsTooExpensive() {
		return numberOfCancellationsTooExpensive;
	}

	public double getPrice(GasType type) {
		return gasPrices.get(type);
	}

	public void setPrice(GasType type, double price) { 
		if(price < 0){
			throw new NegativeAmount();
		}
		gasPrices.put(type, price);
	}

}
