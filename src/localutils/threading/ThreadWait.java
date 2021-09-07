package localutils.threading;

public class ThreadWait {

	public long initialWaitValue;
	public long waitValue;
	public ThreadWaitMultiplier multiplier;

	public ThreadWait() {
		initialWaitValue = 100;
		waitValue = initialWaitValue;
		multiplier = ThreadWaitMultiplier.Fixed;
	}
	public ThreadWait(long initialMilliseconds, ThreadWaitMultiplier waitMultiplier) {
		initialWaitValue = initialMilliseconds;
		waitValue = initialWaitValue;
		multiplier = waitMultiplier;
	}

	public void waitNext() {
		try {
			Thread.sleep(waitValue);
			waitValue *= multiplier.getValue();
		}
		catch (InterruptedException irex) {
			System.out.println(irex.getMessage());
		}
	}

	public void reset() {
		waitValue = initialWaitValue;
	}

	public void reset(long newMilliseconds) {
		waitValue = newMilliseconds;
	}

}
