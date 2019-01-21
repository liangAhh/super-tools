package superTools.File;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 文件工具类
 */
public class FileTool {

    /**
     * spring mvc 上传文件
     * @param file  文件
     * @param pathName  文件保存地址文件名
     */
    public static void upload(MultipartFile file, String pathName) throws IOException {
        File temp = new File(pathName);
        if (!temp.getParentFile().exists()) {
            temp.getParentFile().mkdirs();
        }
        if (!temp.exists()) {
            temp.createNewFile();
        }
        file.transferTo(temp);
    }

    /**
     * @param file  文件
     * @param pathName  文件保存地址文件名
     */
    public static void upload(File file, String pathName) throws IOException {
        FileUtils.copyFile(file, new File(pathName));
    }

    /**
     * @param source    文件来源地址
     * @param pathName  文件保存地址文件名
     */
    public static void upload(String source, String pathName) throws IOException {
        FileUtils.copyFile(new File(source), new File(pathName));
    }

    /**
     * 上传文件至文件夹
     * @param file  文件
     * @param directoryPath 文件夹路径
     * @throws IOException
     */
    public static void uploadFileToDirectory(File file, String directoryPath) throws IOException {
        FileUtils.copyToDirectory(file, new File(directoryPath));
    }

    /**
     * 下载网络文件
     * @param urlFile   网络文件地址
     * @param pathName  保存文件路径文件名
     * @throws IOException
     */
    public static void download(String urlFile, String pathName) throws IOException {
        FileUtils.copyURLToFile(new URL(urlFile), new File(pathName));
    }
}
