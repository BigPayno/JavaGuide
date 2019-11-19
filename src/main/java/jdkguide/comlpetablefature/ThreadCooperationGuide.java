package jdkguide.comlpetablefature;

import javafx.concurrent.Task;
import utils.io.excel.ExcelUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author payno
 * @date 2019/11/18 21:58
 * @description
 */
public class ThreadCooperationGuide {
    public static class OneWayGuide{
        /**
         * 做完事件就什么都不管了
         */
    }
    public static class SyncGuide{
        /**
         * 做完事情等待回应再做事情
         */
    }
    public static class FutureGuide{
        /**
         * 做完事情，并做其他事情，直到自己需要用再去等
         */
    }
    public static class CallBackGuide{
        /**
         * 做完事情，并通知别人完成后该怎么做
         */
    }
}
