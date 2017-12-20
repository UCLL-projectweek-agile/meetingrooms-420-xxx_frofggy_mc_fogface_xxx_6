package ui.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;

import java.lang.annotation.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
import meetingrooms.Service;

public class RequestHandlerFactory{
	
	private Map<String, RequestHandler> handlers = new HashMap<>();

	public RequestHandlerFactory(Service model){
		Reflections reflections = new Reflections("ui.handlers");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Mapping.class);
		for (Class<?> clazz : annotated) {
                    RequestHandler handler;
                    try {
                        handler = (RequestHandler) clazz.newInstance();
                        if(handler != null){
				handler.setModel(model);
				handlers.put(clazz.getAnnotation(Mapping.class).value(), handler);
			}
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(RequestHandlerFactory.class.getName()).log(Level.SEVERE, null, ex);
                    }
		    
		}
	}
	
	public RequestHandler create(String type){
            return handlers.get(type);
	}

}
