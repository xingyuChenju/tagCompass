import com.impinj.octane.ImpinjReader;
import com.impinj.octane.OctaneSdkException;
import com.impinj.octane.Settings;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadTags {
//    public static String path = "F:\\github\\Arrays\\Stochastic resonance\\data\\RFIDdata\\Data0921";
    public static String path = "..\\Matlab\\实验数据20190102\\小白\\";
    public static String TEXTNAME = "postion2_circle2";
    public static String hostname = "169.254.1.6";
    static Writer writer;
    static ArrayList<String> arr = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        try {
            File file = new File(path);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            writer = new FileWriter(path+TEXTNAME+".txt",true);
//            Thread.sleep(15000);
            Toolkit.getDefaultToolkit().beep();
//            String hostname = "speedwayr-10-AB-5A";
//            String hostname = "speedwayr-10-AB-5A";
            ImpinjReader reader = new ImpinjReader();
            reader.connect(hostname);
            reader.applySettings(Settings.load("ReadTags/"+hostname +".xml"));
            reader.setTagReportListener(new TagReportListenerImplementation(TEXTNAME));
            reader.start();
            Scanner s = new Scanner(System.in);
//            for (int k = 0;k<6;k++)
//            {
//                Thread.sleep(20000);
//                Toolkit.getDefaultToolkit().beep();
//            }
            Thread.sleep(46000);

//            long mm = System.currentTimeMillis();
            reader.stop();
//            long m = System.currentTimeMillis();
//            writeFile(longToDate(mm)+'\n');
//            writeFile(longToDate(m)+'\n');
            Toolkit.getDefaultToolkit().beep();
            reader.disconnect();
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
        for (String s:arr)
            writer.write(s);
        writer.flush();
        writer.close();

    }
//    public static double getEntropy(ArrayList<Double> phase_list) {
//        int value_len = (int) Math.round(2 * 3 * Math.PI+1);
//        double[] value_num = new double[value_len];
//        double inter=Math.PI*2/value_len;
//        for (int i = 0; i < phase_list.size(); i++) {
//
//            //int index = (int) Math.round(phase_list.get(i) * 10);
//            int index=(int)(phase_list.get(i)/inter);
//            value_num[index] += 1;
//        }
//
//        double sum = 0;
//        for (int i = 0; i < value_num.length; i++) {
//            if (value_num[i] != 0) {
//                value_num[i] /= phase_list.size();
//                sum += value_num[i] * Math.log(value_num[i]) / Math.log(2);
//            }
//        }
//        return -sum;
//    }
//    public static String longToDate(long lo){
//
//        Date date = new Date(lo);
//
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        return sd.format(date);
//
//    }
//    public static void writeFile(String str) {
//        byte bt[] = new byte[1024];
//        bt = str.getBytes();
//        try {
//            FileOutputStream in = new FileOutputStream(ReadTags.path+ TEXTNAME+".txt", true);
//            try {
//                in.write(bt, 0, bt.length);
//                in.flush();
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
