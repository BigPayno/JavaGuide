package effectivejava.object;

import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.stream.Stream;

/**
 * @author payno
 * @date 2019/8/17 18:34
 * @description R2:遇到多个构造器参数时使用构建器
 * 1.适用于参数过多的情况
 * 2.适用于创建不可变类
 */
public class R2TheUseOfBuilder {
    public static class ImmutablePoint{
        private String name;
        private final int x;
        private final int y;
        private static class Builder{
            private String name="point";
            private final int x;
            private final int y;
            public  Builder(int x,int y){
                this.x=x;
                this.y=y;
            }
            public Builder name(String name){
                this.name=name;
                return this;
            }
            public ImmutablePoint build(){
                return new ImmutablePoint(this);
            }
        }
        public static Builder builder(int x,int y){
            return new Builder(x,y);
        }
        private ImmutablePoint(Builder builder){
            x=builder.x;
            y=builder.y;
        }
    }
    private void immutableBuilder(){
        ImmutablePoint immutablePoint=ImmutablePoint.builder(1,2).name("a point").build();
    }
}
