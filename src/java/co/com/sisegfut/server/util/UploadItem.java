/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.util;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadItem
{
    private CommonsMultipartFile fileData;

    @JsonIgnore(true)
    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
}
