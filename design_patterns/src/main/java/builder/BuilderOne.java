package builder;

import java.text.NumberFormat;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Developer {

	private String name;
	private String position;
	private String[] skills;
	private String[] hobbies;
	private Number salary;

	public Developer(String name, String position, String[] skills, String[] hobbies, Number salary) {
		this.name = name;
		this.position = position;
		this.skills = skills;
		this.hobbies = hobbies;
		this.salary = salary;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String[] getSkills() {
		return this.skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public String[] getHobbies() {
		return this.hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}

	public Number getSalary() {
		return this.salary;
	}

	public void setSalary(Number salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Developer [name=" + this.name + ", position=" + this.position + ", skills=" + Arrays.toString(this.skills) + ", hobbies=" + Arrays.toString(this.hobbies) + ", salary=" + NumberFormat.getInstance().format(this.salary) + "]";
	}

	public static class DeveloperBuilder {
		private String name = null;
		private String position = null;
		private String[] skills = null;
		private String[] hobbies = null;
		private Number salary = -1;

		public DeveloperBuilder name(String name) {
			this.name = name;
			return this;
		}

		public DeveloperBuilder position(String position) {
			this.position = position;
			return this;
		}

		public DeveloperBuilder skills(String... skills) {
			this.skills = skills;
			return this;
		}

		public DeveloperBuilder hobbies(String... hobbies) {
			this.hobbies = hobbies;
			return this;
		}

		public DeveloperBuilder salary(Number salary) {
			this.salary = salary;
			return this;
		}

		public Developer build() {
			boolean valid = Validator.newInstance()
					.notEquals(name, null)
					.notEquals(position, null)
					.notEquals(skills, null)
					.notEquals(hobbies, null)
					.notEquals(salary, -1)
					.getValidateState();
			if (!valid) {
				throw new IllegalStateException("either name, position, skills, hobbies or salary not initialized");
			}
			return new Developer(name, position, skills, hobbies, salary);
		}
	}

	public static DeveloperBuilder builder() {
		return new DeveloperBuilder();
	}

}

public class BuilderOne {

	private static final Logger LOGGER = LogManager.getLogger(BuilderOne.class);

	public static void main(String... args) {
		LOGGER.info("Hello, BuilderOne Design Pattern");

		Developer developer = Developer.builder()
				.name("uday")
				.hobbies("Coding", "Browsing")
				.skills("Java", "J2EE")
				.salary(20_000)
				.position("Java Back-End Developer")
				.build();

		System.out.println(developer);
	}
}
