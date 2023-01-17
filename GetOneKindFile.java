import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GetOneKindFile {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("文件名最好从文件管理器中地址栏复制以防出错");
        System.out.println("输入待提取文件的父目录");
        String baseDirName = scanner.next();
        System.out.println("请输出文件后缀名");
        String suffix = scanner.next();
        System.out.println("提取到哪个文件夹?");
        String destDirName = scanner.next();
            System.out.println("baseDirName: " + baseDirName);
            System.out.println("destDirName: " + destDirName);
        File baseDir = new File(baseDirName);
        ArrayList<File> filesList = new ArrayList<File>();
        filter(baseDir, suffix, filesList);
        File destDir = new File(destDirName);
        destDir.mkdirs();
        transferTo(filesList, destDir);
    }

    public static void starter() throws IOException {
        File baseDir = new File("C:\\Users\\24995\\Desktop\\Typora\\资料");
        ArrayList<File> filesList = new ArrayList<File>();
        filter(baseDir, ".pdf", filesList);
        File destDir = new File("C:\\Users\\24995\\Desktop\\Typora\\资料\\PDF");
        destDir.mkdirs();
        transferTo(filesList, destDir);
    }

    public static void filter(File baseDir, String suffixName, ArrayList<File> files){
        if(baseDir == null || baseDir.listFiles() == null) return;
        for (File file : baseDir.listFiles()) {
            if(file.isDirectory()){
                filter(file, suffixName, files);
            }else if(file.getName().endsWith(suffixName)){
                files.add(file);
            }
        }
    }

    public static void transferTo(ArrayList<File> filesList, File dir) throws IOException {
        String destDir = dir.getAbsolutePath();
        int bytesRead = 0;
        for (File file : filesList) {
            FileInputStream inputStream = new FileInputStream(file);
            String destFileName = destDir + "\\" + file.getName();
            FileOutputStream outputStream = new FileOutputStream(destFileName);
            byte buf[] = new byte[4 * 1024];
            while ((bytesRead = inputStream.read(buf)) != -1){
                outputStream.write(buf, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        }
    }
}
