package guava.collection;

import com.google.common.base.CharMatcher;
import com.google.common.collect.FluentIterable;
import com.google.common.io.Files;

import java.io.File;
import java.util.Optional;

/**
 * @author payno
 * @date 2019/8/16 10:33
 * @description
 */
public class FluentIterableGuide {
    public static void main(String[] args) {
        FluentIterable<File> fluentIterable= Files.fileTreeTraverser().breadthFirstTraversal(new File("d://"));
        fluentIterable.filter(Files.isDirectory())
                .transform(File::getName)
                .filter(name->CharMatcher.javaLetter().matchesAllOf(name))
                .forEach(System.out::println);
    }
}
