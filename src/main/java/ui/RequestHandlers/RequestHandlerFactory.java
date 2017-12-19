package ui.RequestHandlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;

import java.lang.annotation.*; 
import meetingrooms.Service;

public class RequestHandlerFactory{
	
	private Map<String, RequestHandler> handlers = new HashMap<>();

	public RequestHandlerFactory(Service model) throws InstantiationException, IllegalAccessException{
		Reflections reflections = new Reflections("view.handlers");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Mapping.class);
		for (Class<?> clazz : annotated) {
			RequestHandler handler = (RequestHandler) clazz.newInstance();
		    if(handler != null){
				handler.setModel(model);
				handlers.put(clazz.getAnnotation(Mapping.class).value(), handler);
			}
		}
	}
	
	public RequestHandler create(String type){
            return handlers.get(type);
	}

}
