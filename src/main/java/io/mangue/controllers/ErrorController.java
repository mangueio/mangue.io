package io.mangue.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by misael on 05/12/2015.
 */
@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private static final String PATH = "/error";

    @Value("${debug:true}")
    private boolean debug;

    @Autowired
    private ErrorAttributes errorAttributes;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ViewResolver viewResolver;

    @RequestMapping(value = PATH)
    public ResponseEntity<String> error(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ct = request.getContentType();
        String accept = request.getHeader("Accept");
        String body = null;

        HttpHeaders headers = new HttpHeaders();
        int status = response.getStatus();
        ErrorWrapper errors = new ErrorWrapper(status, getErrorAttributes(request, debug));

        if((accept != null && !accept.isEmpty()) && containsHtml(accept)) {

            View view = null;
            if(errors.status == HttpStatus.UNAUTHORIZED.value())
                view = viewResolver.resolveViewName("unauthorized", null);
            else
                view = viewResolver.resolveViewName("error", null);

            view.render(errors.errorAttributes, request, response);

            headers.setContentType(MediaType.TEXT_HTML);
            return new ResponseEntity<String>(body, headers, HttpStatus.valueOf(status));
        }else{
            headers.setContentType(MediaType.APPLICATION_JSON);
            body = mapper.writeValueAsString(errors);
            return new ResponseEntity<String>(body, headers, HttpStatus.valueOf(status));
        }
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * Get extract error attributes from request
     * @param request
     * @param includeStackTrace
     * @return
     */
    private Map<String, ?> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

    private boolean containsHtml(String type){
        return type != null && !type.isEmpty() && type.toLowerCase().contains(MediaType.TEXT_HTML_VALUE);
    }

    private boolean isHtmlType(String type){
        return type != null && !type.isEmpty() && type.toLowerCase().equals(MediaType.TEXT_HTML_VALUE);
    }

    private boolean isJsonType(String type){
        return type != null && !type.isEmpty() && type.toLowerCase().equals(MediaType.APPLICATION_JSON);
    }

    /**
     * Error Object Wrapper to be sent as Json or Displayed in template
     */
    public static class ErrorWrapper {
        public String timestamp;
        public Integer status;
        public String error;
        public String exception;
        public String message;
        public String path;
        @JsonIgnore
        public Map<String, ?> errorAttributes;

        public ErrorWrapper(int status, Map<String, ?> errorAttributes) {
            this.status = status;
            this.error = (String) errorAttributes.get("error");
            this.message = (String) errorAttributes.get("message");
            this.exception = (String) errorAttributes.get("exception");
            this.timestamp = errorAttributes.get("timestamp").toString();
            this.path = (String) errorAttributes.get("path");
            this.errorAttributes = errorAttributes;
        }
    }
}