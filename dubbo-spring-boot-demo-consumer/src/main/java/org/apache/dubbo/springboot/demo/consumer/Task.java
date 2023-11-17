package org.apache.dubbo.springboot.demo.consumer;

import org.apache.commons.cli.CommandLineParser;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.DemoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author malingdi
 * @description
 * @date 2023/11/17 15:28
 */
@Component
public class Task implements CommandLineRunner {

    @DubboReference
    private DemoService demoService;

    @Override
    public void run(String... args) throws Exception {
        String result = demoService.sayHello("world");
        System.out.println("Reactive result =====>" + result);

        new Thread(()-> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date() + "Reactive result =======>" + demoService.sayHello("world"));
                }catch (InterruptedException e){
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
