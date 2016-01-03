package io.mangue.aspect;

import io.mangue.aspect.annotations.SubdomainMapping;
import io.mangue.config.multitenance.TenantContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Created by misael on 04/12/2015.
 * This class throws a not found exception for request that nave a @SubdomainMapping
 * annotation with a value that does not corresponde to the TenantContextHolder properies
 * @see SubdomainTypes
 * @see SubdomainMapping
 * @see io.mangue.controllers.PagesController
 */
@Aspect
@Controller
public class WebAspect {
    @Before("@annotation(subdomainMapping)")
    public void subdomainMapping(SubdomainMapping subdomainMapping) throws Throwable {
        if(subdomainMapping.value().equals(SubdomainTypes.ALL))
            return;
        if(subdomainMapping.value().equals(SubdomainTypes.NONE) && TenantContextHolder.getTenantSubdomain() != null)
            throw new ResourceNotFoundException();
        if(subdomainMapping.value().equals(SubdomainTypes.CONSOLE) && !TenantContextHolder.isTenantConsoleRequest()){
            throw new ResourceNotFoundException();
        }else if(subdomainMapping.value().equals(SubdomainTypes.APP) && TenantContextHolder.getTenantSubdomain() == null){
            throw new ResourceNotFoundException();
        }
	}
}
