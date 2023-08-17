package abstract_pattern;

import java.util.List;
import java.util.Map;

import abstract_pattern.domain.Car;
import abstract_pattern.domain.enums.Property;

public class App {

	public static void main(String... args) {
		System.out.println("Hey, Abstract Document!");

		Map<String, Object> wheelProperties = Map.of(Property.MODEL.toString(), "15C", Property.PRICE.toString(), 100L, Property.TYPE.toString(), "wheel");
		Map<String, Object> doorProperties = Map.of(Property.MODEL.toString(), "Lambo", Property.PRICE.toString(), 300L, Property.TYPE.toString(), "door");
		Map<String, Object> carProperties = Map.of(Property.MODEL.toString(), "300SL", Property.PRICE.toString(), 10000L, Property.TYPE.toString(), "car", Property.PARTS.toString(), List.of(wheelProperties, doorProperties));

		Car car = new Car(carProperties);

		System.out.println(car);
		System.out.println("--------------------------------------");
		car.children(Property.PARTS.toString(), e -> e).forEach(System.out::println);
	}

}
