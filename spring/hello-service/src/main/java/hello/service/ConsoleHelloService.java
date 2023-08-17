package hello.service;

public class ConsoleHelloService implements HelloService {
	
	private final String prefix;
	
	private final String suffix;


	public ConsoleHelloService(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	@Override
	public void sayHello(String name) {
		String msg = String.format("%s %s %s", prefix, name, suffix);
		System.out.println(msg);
	}
}
