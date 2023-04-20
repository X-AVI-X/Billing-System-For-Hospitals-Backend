package billing.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Long value;
    public ResourceNotFound(String s){
        super(s);
    }

    public ResourceNotFound(String resourceName, String fieldName, Long value){
        super(String.format("%s not found with %s : '%s",resourceName,fieldName,value));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
