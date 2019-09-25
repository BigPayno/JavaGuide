package utils.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

@Data
public final class Part {
	private long from;
	private long to;
	private long repeat;
	private boolean reverse;
	private Part(long from) {
		this.from=from;
		this.to=from;
		this.repeat=1;
		this.reverse=false;
	}
	private Part(long from,long to) {
		this.from=from;
		this.to=to;
		this.repeat=1;
		this.reverse=false;
	}
	public static Part copy() {
		return new Part(0,-1);
	}
	public static Part of(long index) {
		return new Part(index);
	}
	public static Part of(long from,long to) {
		return new Part(from,to);
	}
	public Part repeat(long repeat) {
		this.repeat=(repeat>0)?repeat:1;
		return this;
	}
	public Part reverse() {
		this.reverse=!(this.reverse);
		return this;
	}
	public <T> Stream<T> from(ReusableStream<T> reusableStream){
		return reusableStream.of(this);
	}
	public <T> Stream<T> from(List<T> list){
		return ReusableStream.from(list).of(this);
	}
	public <T> Stream<T> from(Stream<T> stream){
		return ReusableStream.from(stream.collect(Collectors.toList())).of(this);
	}
}
