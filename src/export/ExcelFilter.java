/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package export;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Andrey
 */
public class ExcelFilter extends FileFilter {
          private String extension = "xls";

      public String getDescription() {
        return "(*" + extension + ")";
      } 

    /**
     *
     * @param file
     * @return
     */
    @Override
    public boolean accept(File file) {
        return (file.getName().endsWith(extension));
    }
}
