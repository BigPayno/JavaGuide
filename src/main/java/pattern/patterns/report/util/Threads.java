package pattern.patterns.report.util;

public final class Threads {
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
