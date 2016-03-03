package export;

import data.Data;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
public class ExportExcel
{

 Data data;   
  private File inputWorkbook;
  private File outputWorkbook;

  public ExportExcel()
  {
     try {
         inputWorkbook = new File(".\\etc\\example2.xls");
         outputWorkbook = new File("D:\\output2.xls");
         data = gui.Form.data;
         
         readWrite();
     } catch (IOException ex) {
         Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
     } catch (BiffException ex) {
         Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
     } catch (WriteException ex) {
         Logger.getLogger(ExportExcel.class.getName()).log(Level.SEVERE, null, ex);
     }
  }

  final void readWrite() throws IOException, BiffException, WriteException
  {
    Workbook w1 = Workbook.getWorkbook(inputWorkbook);

    WritableWorkbook w2 = Workbook.createWorkbook(outputWorkbook, w1);
      
    modify(w2);
    
    w2.write();
    w2.close();
  }

  void modify(WritableWorkbook w) throws WriteException, IOException
  {
    WritableSheet sheet = w.getSheet(0);
    int rowIndex = 8;
    Label l;
    for (String student: data.getLastNames()){
        l = new Label(0, rowIndex, student);
        sheet.addCell(l);
        for(String[] skip : data.getSkips(data.getIdOnLastName(student))){
            int colIndex = Integer.parseInt(skip[1].substring(8, 10));   
            l = new Label(colIndex, rowIndex, skip[2]);
            sheet.addCell(l);
        }
        rowIndex++;
    }
  }
}









