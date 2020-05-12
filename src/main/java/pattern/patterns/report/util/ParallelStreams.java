package pattern.patterns.report.util;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Stream;


/*
 *	 因为发现并发流中由主线程执行的数据库保存操作会失效
 *	 因此使用该类维护该bug，以防止最终结果与预期的不一致
 * ParallelStream+Repository+mainThread->bug
 *	 懒得使用多线程或者FutureTask，也懒得看源码。。
 * 
 * 1.因为使用并发操作，所以使用同步并发容器，考虑到写多，所以不用CopyOnWrite
 * 
 * 2.突然发现只要是forEach中使用main线程保存就会失败
 */
public final class ParallelStreams {
	
	private static final String MAIN_THREAD="main";
	private static final String HTTP_THREAD_PREFIX="http";
	
	private static boolean isVaildThread(Thread thread) {
		String threadName = thread.getName();
		return MAIN_THREAD.equals(threadName)||
			   threadName.startsWith(HTTP_THREAD_PREFIX);
	}
	
	public static <E> void invokeAll(Collection<E> elements,Consumer<? super E> action) {
		if(elements.size()==1) {
			Stream.concat(elements.parallelStream(), elements.parallelStream())
				.forEach(ele->{
					if(!isVaildThread(Thread.currentThread())){
						action.accept(ele);
					}
			});
		}else {
			LinkedBlockingQueue<E> queue=new LinkedBlockingQueue<E>();
			elements.parallelStream().forEach(ele->{
				if(isVaildThread(Thread.currentThread())) {
					queue.add(ele);
				}else {
					action.accept(ele);
				}
			});
			if(!queue.isEmpty()) {
				invokeAll(queue, action);
			}
		}
	}
}
