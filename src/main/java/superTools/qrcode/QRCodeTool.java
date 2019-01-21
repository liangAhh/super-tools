package superTools.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Objects;

/**
 * 二维码工具类
 */
public class QRCodeTool {

    private static final int QRCOLOR = 0xFF000000; // 默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF; // 背景颜色

    // 用于设置QR二维码参数
    private static Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;

        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置编码方式
            put(EncodeHintType.MARGIN, 0);
        }
    };

    /**
     * 创建二维码到文件
     * @param width     二维码宽
     * @param height    二维码高
     * @param Context   二维码内容
     * @param file  二维码写入的文件对象
     */
    public static void createQrcode(int width, int height, String Context, File file) throws Exception {
        String format = "jpg";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(Context, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToFile(bitMatrix, format, file);
    }

    /**
     * 创建二维码到输出流
     * @param width     二维码款
     * @param height    二维码高
     * @param Context   二维码内容
     * @param response  响应对象
     */
    public static void createQrcode(int width, int height, String Context, HttpServletResponse response) throws IOException {
        ServletOutputStream stream = response.getOutputStream();
        try {
            String format = "jpg";
            BitMatrix bitMatrix = new MultiFormatWriter().encode(Context, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }

    /**
     * 生成logo二维码
     * @param logoFile  logo文件
     * @param codeFile  二维码写入的文件
     * @param Context   扫描内容
     * @param width     二维码宽
     * @param height    二维码高
     */
    public static void drawLogoQRCode(File logoFile, File codeFile, String Context, int width, int height){
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bm = multiFormatWriter.encode(Context, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }
            int width1 = image.getWidth();
            int height1 = image.getHeight();
            if (Objects.nonNull(logoFile) && logoFile.exists()) {
                Graphics2D g = image.createGraphics();
                BufferedImage logo = ImageIO.read(logoFile);
                g.drawImage(logo, width1 * 2 / 5, height1 * 2 / 5, width1 * 2 / 10, height1 * 2 / 10, null);
                g.dispose();
                logo.flush();
            }
            image.flush();
            ImageIO.write(image, "png", codeFile); // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建带背景图的二维码
     * @param QRFile    原二维码文件
     * @param backgroundImgFile     背景图文件
     * @param newQRFile     生成图片写入的文件
     * @param newQRWidth    二维码图片的宽度
     * @param newQRHeight   二维码图片的高度
     * @param QRX           二维码图片中的二维码 x 坐标
     * @param QRY           二维码图片中的二维码 y 坐标
     * @param QRWidth       二维码图片中的二维码宽度
     * @param QRHeight      二维码图片中的二维码高度
     * @throws IOException
     */
    public static void createBackgroundQR(File QRFile, File backgroundImgFile, File newQRFile, int newQRWidth, int newQRHeight, int QRX, int QRY, int QRWidth, int QRHeight) throws IOException {
        BufferedImage image = new BufferedImage(newQRWidth, newQRHeight, BufferedImage.TYPE_INT_RGB);
        Graphics backgroundImg = image.getGraphics();
        BufferedImage bimg = null;
        bimg = ImageIO.read(backgroundImgFile);
        backgroundImg.drawImage(bimg, 0, 0, newQRWidth, newQRHeight, null);
        backgroundImg.dispose();
        Graphics QRImg = image.getGraphics();
        BufferedImage qrimg = null;
        qrimg =  ImageIO.read(QRFile);
        QRImg.drawImage(qrimg, QRX, QRY, QRWidth, QRHeight, null);
        QRImg.dispose();
        ImageIO.write(image, "jpg", newQRFile);
    }
}
