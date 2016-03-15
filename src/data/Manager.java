package data;




public class Manager {
    DataSource dataSource;
    String[][] table;

    public Manager() {
        this.dataSource = new DataSource();
    }
    public void initTable(String nameTable){
        table = dataSource.getTab("select * from " + nameTable + ";");
    }
    
    
    String[] uniqArr(String[] arrIn){
        for (int i = 0; i < arrIn.length; i++){
            for (int j = i + 1; j < arrIn.length; j++){
                if (i != j){
                    if (arrIn[j].equals(arrIn[i])){
                    arrIn[i] = "";
                    }
                }
            }
        }
        int u = 0;
        for (String arrIn1 : arrIn) {
            if (!(arrIn1.equals(""))) {
                u++;
            }
        }
        String[] arrOut = new String[u];
        int j = 0;
        for (String arrIn1 : arrIn) {
            if (!(arrIn1.equals(""))) {
                arrOut[j] = arrIn1;
                j++;
            }
        }
        return arrOut;
    }
}
