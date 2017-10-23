/**
 * 
 */
package rw.efile.test;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Component;
 
@Component
@ManagedBean(name="treeIconView")
public class IconView {
     
    private TreeNode root;
     
    @ManagedProperty("#{documentService}")
    private DocumentService service;
     
    @PostConstruct
    public void init() {
        root = service.createDocuments();
    }
 
    public void setService(DocumentService service) {
        this.service = service;
    }
 
    public TreeNode getRoot() {
        return root;
    }
    
}
