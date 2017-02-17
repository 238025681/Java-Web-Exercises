/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.util;

import bg.home.webServer.server.routing.Controller;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kalin
 */
public class DirectoryViewer {

    public static Class[] findControllers(String initialPathString) throws ClassNotFoundException {
        File[] classes = getClasses(initialPathString);

        List<Class> controller = new LinkedList<>();

        for (File file : classes) {
            String fileName = file.getAbsolutePath().split("classes")[1].replace("\\", ".");
            Class clazz = Class.forName(fileName.substring(1, fileName.length() - 6));
            if (clazz.isAnnotationPresent(Controller.class)) {
                controller.add(clazz);
            }
        }

        return controller.toArray(new Class[0]);
    }

    private static File[] getClasses(String initialPathString) {
        Deque<File> fileQueue = new ArrayDeque<>();
        List<File> listOfFiles = new ArrayList<>();
        fileQueue.push(new File(initialPathString));
        while (!fileQueue.isEmpty()) {
            File file = fileQueue.poll();
            if (file.isDirectory()) {
                Collections.addAll(fileQueue, file.listFiles());
            } else if (file.getName().endsWith(".class")) {
                listOfFiles.add(file);
            }
        }
        return listOfFiles.toArray(new File[listOfFiles.size()]);
    }
}
