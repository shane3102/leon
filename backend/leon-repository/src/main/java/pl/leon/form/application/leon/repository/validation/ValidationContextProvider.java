package pl.leon.form.application.leon.repository.validation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ValidationContextProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ValidationContextProvider.applicationContext = applicationContext;
    }

    public static Object getBean(Class cls) {
        return ValidationContextProvider.applicationContext.getBean(cls);
    }
}
