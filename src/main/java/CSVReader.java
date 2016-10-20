import java.util.ArrayList;

/**
 * Created by zhangdenghui on 16/10/13.
 */

public class CSVReader {
    public static ArrayList<String> readline(String filepath ,int num_lines){
        ArrayList<String> content=null;
        long startTime=System.currentTimeMillis();   //获取开始时间
        try {
            content=HDFSAccess.readHDFSFile(filepath,num_lines);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(-1);
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
//        System.out.println("读HDFS cost： "+(endTime-startTime)+"ms");
        return content;
    }

    public static String[] getnames(ArrayList<String> content) {

        String[] names=content.get(0).split(",");
        return names;
    }

    public static String[] gettypes(ArrayList<String> content) {

        String[] types=content.get(1).split(",");
        for(int i=0;i<types.length;i++){
            types[i]=RegularMatch.matchType(types[i]);
        }
        return types;
    }


    public static void main(String[] args)
    {
        String filepath="/user/did/zhangdenghui/csvtest.csv";
        ArrayList<String> content=readline(filepath,100);
        String[] names=getnames(content);
        String[] types=gettypes(content);

        for(int i=0;i<names.length;i++){
            System.out.println(names[i]+" "+types[i]);
        }
    }
}
