package threadutils;

public class ThreadWait {

	public long waitValue;
	public int multiplier; // 1 = linear, 2 = exponential

	public ThreadWait() {
		waitValue = 100;
		multiplier = 2;
	}
	public ThreadWait(long initialMilliseconds, int multiplier) {
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
