package active_object;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskExecutor {

	private String name;
	
	private int state;
	private Thread thread;
	private BlockingQueue<Runnable> queue;

	public TaskExecutor() {
		this("task executor");
	}

	public TaskExecutor(String name) {
		this.state = 0;
		this.name = name;
		
		Runnable run = () -> {
			boolean infinite = true;
			while (infinite) {
				try {
					queue.take().run();
				} catch (InterruptedException e) {
				}
			}
			
		};
		
		queue = new LinkedBlockingQueue<>();
		thread = new Thread(run);
		thread.start();
	}
}
