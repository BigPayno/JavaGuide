package utils.stream;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Streams;



public final class ReusableStream<T>{
	List<T> selectable=null;
	private ReusableStream(List<T> sources){selectable=Objects.requireNonNull(sources);}
	public static <T> ReusableStream<T> from(List<T> sources){
		return new ReusableStream<T>(sources);
	}
	@SafeVarargs
	public final ReusableStream<T> compose(Part ...parts){
		List<T> sources=of(parts).collect(Collectors.toList());
		return new ReusableStream<T>(sources);
	}
	private long preIndex(long index) { return index>=0?index:(index+=selectable.size());}
	public Stream<T> of(long from){
		from=preIndex(from);
		return (from!=0)
				?selectable.stream().skip(from).limit(1)
				:selectable.stream().limit(1);
	}
	public Stream<T> of(long from,long to){
		from=preIndex(from);
		to=preIndex(to);
		if(from==to) {
			return of(from);
		}else {
			return (from!=0)
					?selectable.stream().skip(from).limit(to+1)
					:selectable.stream().limit(to+1);
		}
	}
	public Stream<T> of(Part part){
		Stream<T> result=Stream.empty();
		if(part.getRepeat()==1) {
			result =of(part.getFrom(),part.getTo());
		}else {
			long repeat=part.getRepeat();
			while(repeat>0) {
				result=Streams.concat(result,of(part.getFrom(),part.getTo()));
				repeat--;
			}
		}
		if(part.isReverse()) {
			result=Lists.reverse(result.collect(Collectors.toList())).stream();
		}
		return result;
	}
	@SafeVarargs
	public final Stream<T> of(Part ...parts){
		Stream<T> result=Stream.empty();
		for(Part part:parts) {
			result=Streams.concat(result,of(part));
		}
		return result;
	}
}
