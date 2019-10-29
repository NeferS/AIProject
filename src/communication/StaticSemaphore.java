package communication;

import java.util.concurrent.Semaphore;

public final class StaticSemaphore {
	private static Semaphore s = new Semaphore(0);
	
	public static void acquire() throws InterruptedException { s.acquire(); }
	public static void release() { s.release(); }
}
