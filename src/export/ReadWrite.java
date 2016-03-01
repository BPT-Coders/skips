package export;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import data.Data;
public class ReadWrite
{

 Data data;   
  private File inputWorkbook;
  private File outputWorkbook;

  public ReadWrite(String input, String output)
  {
    inputWorkbook = new File(input);
    outputWorkbook = new File(output);
    data = gui.Form.data;
  }

  public void readWrite() throws IOException, BiffException, WriteException
  {
    Workbook w1 = Workbook.getWorkbook(inputWorkbook);

    WritableWorkbook w2 = Workbook.createWorkbook(outputWorkbook, w1);
      
    modify(w2);
    
    w2.write();
    w2.close();
  }

  private void modify(WritableWorkbook w) throws WriteException, IOException
  {
    WritableSheet sheet = w.getSheet(0);
    int rowIndex = 8;
    for (String student: data.getLastNames()){
        Label l = new Label(0, rowIndex++, student);
        sheet.addCell(l);
    }   
  }
}









