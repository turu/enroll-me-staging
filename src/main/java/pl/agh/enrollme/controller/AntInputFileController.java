package pl.agh.enrollme.controller;

import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Michal Partyka
 */
@Controller
public class AntInputFileController {
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
