package jdkguide.thread.balking;

import lombok.AllArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author payno
 * @date 2019/11/7 10:14
 * @description
 */
@lombok.Data
@AllArgsConstructor
public class Data {
        private String filename;
        private String content;
        private boolean changed;
        public Data(String filename, String content) {
            this.filename = filename;
            this.content = content;
            this.changed = true;
        }
        // 修改数据
        public synchronized void change(String newContent) {
            content = newContent;
            changed = true;
        }
        // 若数据有修改，则保存，否则直接返回
        public synchronized void save() throws IOException {
            if (!changed) {
                System.out.println(Thread.currentThread().getName() + " balks");
                return;
            }
            doSave();
            changed = false;
        }
        private void doSave() throws IOException {
            System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
            Writer writer = new FileWriter(filename);
            writer.write(content);
            writer.close();
        }
}
