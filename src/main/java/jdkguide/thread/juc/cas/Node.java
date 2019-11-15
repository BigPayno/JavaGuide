package jdkguide.thread.juc.cas;

import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author payno
 * @date 2019/11/15 11:12
 * @description
 */
@RequiredArgsConstructor
@ToString
public class Node {
    @NotNull
    public final String item;
    public Node next;
}
