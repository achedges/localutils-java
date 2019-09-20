package threadutils;

public class ThreadUtils {

	private long waitValue;
	private int multiplier; // 1 = linear, 2 = exponential

	public ThreadUtils() {
		waitValue = 100;
		multiplier = 2;
	}
	public ThreadUtils(long initialMilliseconds, int multiplier) {
		this.waitValue = initialMilliseconds;
		this.multiplier = multiplier;
	}

	public void waitNext() {
		try {
			Thread.sleep(waitValue);
			waitValue *= multiplier;
		}
		catch (InterruptedException irex) {
			System.out.println(irex.getMessage());
		}
	}

}
