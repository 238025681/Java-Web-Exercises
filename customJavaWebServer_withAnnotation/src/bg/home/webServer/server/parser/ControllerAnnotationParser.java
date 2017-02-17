/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.home.webServer.server.parser;

import bg.home.webServer.server.enumeratoin.HttpRequestType;
import bg.home.webServer.server.provider.ClassProvider;
import bg.home.webServer.server.routing.Controller;
import bg.home.webServer.server.routing.ControllerActionPair;
import bg.home.webServer.server.routing.RequestMapping;
import bg.home.webServer.server.routing.UriParameter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author kalin
 */
public class ControllerAnnotationParser implements AnnotationParser<HttpRequestType, Map<String, ControllerActionPair>> {

    private ClassProvider classProvider;

    public ControllerAnnotationParser(ClassProvider classProvider) {
        this.classProvider = classProvider;
    }

    private String createMappingRegex(Method currentMethod, String mapping,
            List<String> mappingTokens, Map<Integer, Class> argumentMapping) {
        for (int i = 0; i < mappingTokens.size(); i++) {
            if (!(mappingTokens.get(i).startsWith("{")) && (mappingTokens.get(i).startsWith("{"))) {
                continue;
            }

            for (Parameter parameter : currentMethod.getParameters()) {
                if (!parameter.isAnnotationPresent(UriParameter.class)) {
                    continue;
                }
                UriParameter uriParameter = parameter.getAnnotation(UriParameter.class);
                if (mappingTokens.get(i).equals("{" + uriParameter.value() + "}")) {
                    argumentMapping.put(i, parameter.getType());

                    String regexReplacement = parameter.getType() == String.class ? "[a-zA-Z]+" : "[0-9]+";
                    mapping = mapping.replace(mappingTokens.get(i), regexReplacement);
                    break;
                }
            }
        }
        return mapping;
    }

    @Override
    public void parse(Map<HttpRequestType, Map<String, ControllerActionPair>> routes) throws IllegalAccessException, InstantiationException {
        Class[] classes = this.classProvider.getClassesByAnnotation(Controller.class);
        for (Class currentClass : classes) {
            Method[] methods = Arrays.stream(currentClass.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                    .toArray(Method[]::new);
            for (Method currentMethod : methods) {
                RequestMapping requestMapping = currentMethod.getAnnotation(RequestMapping.class);
                HttpRequestType requestMethod = requestMapping.method();
                String url = requestMapping.value();
                List<String> urlTokens = Arrays.stream(url.split("/")).collect(Collectors.toList());
                Map<Integer, Class> argumentMapping = new HashMap<>();
                url = this.createMappingRegex(currentMethod, url, urlTokens, argumentMapping);
                
                Object controllerIstance = currentClass.newInstance();
                
                ControllerActionPair actionPair = new ControllerActionPair(currentMethod, controllerIstance, argumentMapping);
                if (!routes.containsKey(requestMethod)) {
                    routes.put(requestMethod, new HashMap<>());
                }
                
                routes.get(requestMethod).put(url, actionPair);
            }
        }

    }

}
