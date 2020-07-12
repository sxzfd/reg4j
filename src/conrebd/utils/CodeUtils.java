/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conrebd.utils;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author knightsong
 */
public class CodeUtils {
    
    public static List<String> getRootCause(String filePath,int start,int end) throws Exception{
        File file =new File(filePath);
        List<String> result =new ArrayList<>();
        FileReader  fr =new  FileReader(file);
        LineNumberReader lineNumberReader=new LineNumberReader(fr);
        String line;
        int lno=1;
        while(lno<=end){
        line=lineNumberReader.readLine();
        line=line.replaceAll("\n", "").trim();
            if (!"".equals(line) || line!=null) {
                if (lno>=start) {
                     result.add(line);
                }              
            }
            lno=lineNumberReader.getLineNumber()+1;
        }
       return result;
    }

    public static List<String> getRootCause(String rootCause,String bugPath) throws Exception{
        String[] sr=rootCause.split(",");
        String path=bugPath+File.separator+"regression"+File.separator+sr[0].trim();
        int start=Integer.parseInt(sr[1].trim());
        int end =Integer.parseInt(sr[2].trim());
        if (end<start) {
            throw  new Exception("Wrong root cause range");
        }
        return  getRootCause(path,start,end);
    }
    
      public static List<String> getRootFixed(String filePath,int start,int end) throws Exception{
        File file =new File(filePath);
        List<String> result =new ArrayList<>();
        FileReader  fr =new  FileReader(file);
        LineNumberReader lineNumberReader=new LineNumberReader(fr);
        String line;
        int lno=1;
        while(lno<=end){
        line=lineNumberReader.readLine();
        line=line.replaceAll("\n", "").trim();
            if (!"".equals(line) || line!=null) {
                if (lno>=start) {
                     result.add(line);
                }              
            }
            lno=lineNumberReader.getLineNumber()+1;
        }
       return result;
    }
       public static List<String> getRootFixed(String rootFixed,String bugPath) throws Exception{
        String[] sr=rootFixed.split(",");
        String path=bugPath+File.separator+"fixed"+File.separator+sr[0].trim();
        int start=Integer.parseInt(sr[1].trim());
        int end =Integer.parseInt(sr[2].trim());
        if (end<start) {
            throw  new Exception("Wrong root cause range");
        }
        return  getRootCause(path,start,end);
    }
      public static List<String> getRootFixed(){
          String ss="if (idleObjects.isEmpty() && idleObjects.hasTakeWaiters()) {\n" +
"            // POOL-356.\n" +
"          // In case there are already threads waiting on something in the pool\n" +
"           // (e.g. idleObjects.takeFirst(); then we need to provide them a fresh instance.\n" +
"            // Otherwise they will be stuck forever (or until timeout)\n" +
"            final PooledObject<T> freshPooled = create();\n" +
"            idleObjects.put(freshPooled);";
          
        String[] sr=ss.split("\n");
        List<String> sT=new ArrayList();
          for (int i = 0; i < sr.length; i++) {
              String string = sr[i];
              sT.add(string);
              
          }
        return  sT;
    }
}
