package devcast.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * @author mzielinski on 16.12.14.
 */
@ManagedBean
@RequestScoped
public class DefaultBean implements Serializable {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}