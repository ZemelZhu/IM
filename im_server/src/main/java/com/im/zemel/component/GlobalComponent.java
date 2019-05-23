package com.im.zemel.component;

import com.im.zemel.inf.IComponent;
import com.im.zemel.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlobalComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalComponent.class);

    private static final GlobalComponent INSTANCE = new GlobalComponent();
    /**
     * 组件集合
     */
    private Map<String, IComponent> modules;

    /**
     * 任务线程池
     */
    private ExecutorService userCmdThreadPool;

    private GlobalComponent() {
        modules = new ConcurrentHashMap<String, IComponent>();
        userCmdThreadPool = Executors.newFixedThreadPool(4);
    }

    public static GlobalComponent getInstance() {
        return INSTANCE;
    }

    public void addComponent(String name, IComponent component) {
        modules.put(name, component);
    }

    public void addTask(Task task) {
        userCmdThreadPool.submit(task);
    }

    public ExecutorService getUserCmdThreadPool() {
        return userCmdThreadPool;
    }

    public IComponent getComponent(String componentName) {
        return modules.get(componentName);
    }

}
