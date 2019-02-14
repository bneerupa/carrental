import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental.bean.Car;

/**
 * 
 */

/**
 * @author neerupa
 *
 */
public class CarUtil {

	/**
	 * @param args
	 */
	ObjectMapper mapper = new ObjectMapper();
	
	public List<Car> getCar(String responseJson) {
		
		List<Car> cars=null;
		
		try {
			cars = mapper.readValue(responseJson, mapper.getTypeFactory().constructCollectionType(List.class, Car.class));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cars;
	}

	public void validateMakeAndColor(List<Car> cars, String make, String color)
	{
			
			for(Car car:cars) {
				if(car.getMetadata().getColor().equals(color) && car.getMake().equals(make)) {
					System.out.println("Make :" + make + ", Model :"+car.getModel() + ", Notes :"+ car.getMetadata().getNotes());
				}
			}

	
	}
	public List<Car> lowestRental(List<Car> cars, Boolean discount){
		
		
		if(cars.isEmpty() || cars == null) {
			return null;
		}
		List<Car> lowestcars=new ArrayList<Car>();
		Float lowprice = null;

		if(discount) {
			lowprice = getDiscountedPrice(cars.get(0));
		}else
			lowprice = cars.get(0).getPerdayrent().getPrice();
		for(Car car:cars) {
			Float toCompare = null;
			
			if(discount) {
				toCompare = getDiscountedPrice(car);
			}else
				toCompare = car.getPerdayrent().getPrice();
			
			if(lowprice > toCompare) {
				lowestcars.clear();
				lowprice=toCompare;
				lowestcars.add(car);
				
			}else if(lowprice.equals(toCompare))
				lowestcars.add(car);
		}

		return lowestcars;
		
	}
	private float getDiscountedPrice(Car car) {
		float discountedPrice = car.getPerdayrent().getPrice() * ((100-car.getPerdayrent().getDiscount())/100);
		return discountedPrice;
	}
	
	private float getRevenue(Car car) {
		Float revenue = (getDiscountedPrice(car) * car.getMetrics().getRentalcount().getYeartodate()) - (car.getMetrics().getDepreciation() + car.getMetrics().getYoymaintenancecost());
		return revenue;
	}
	public Car heighestRevenueCar(List<Car> cars) {
		if(cars.isEmpty() || cars == null) {
			return null;
		}
		Car heighestRevenueCar = cars.get(0);
		Float heighestRevenue = getRevenue(cars.get(0));
		
		for(Car car:cars) {
			Float revenueToCompare = getRevenue(car);
			if(revenueToCompare > heighestRevenue) {
				heighestRevenue = revenueToCompare;
				heighestRevenueCar = car;
			}
		}
		return heighestRevenueCar;
			
	}
	public static void main(String[] args) {
		CarUtil carColor = new CarUtil();
		String json = "[{\"make\": \"Tesla\",\"model\": \"A7\",\"vin\": \"09AGHY64352JITEG98K\",\"metadata\": {\"color\": \"Blue\",\"notes\": \"Scratches on the front side of the car\"},\"perdayrent\": {\"price\": 140,\"discount\": 15},\"metrics\": {\"yoymaintenancecost\": 2190.76,\"depreciation\": 2256.43,\"rentalcount\": {\"lastweek\": 4,\"yeartodate\": 221}}}]";
		List<Car> tesla = carColor.getCar(json);
		carColor.validateMakeAndColor(tesla, "Tesla", "Blue");
		
		
	

	}

}
