package fr.arthaudproust.webcam;


import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.IplImage;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Webcam {

    private final FrameGrabber grabber = new OpenCVFrameGrabber(0);
    private final OpenCVFrameConverter.ToIplImage iplConverter = new OpenCVFrameConverter.ToIplImage();
    private final Java2DFrameConverter frameConverter = new Java2DFrameConverter();

    public void run() throws FrameGrabber.Exception {
        grabber.start();

        Frame frame = grabber.grab();

        IplImage img = iplConverter.convert(frame);

        CanvasFrame canvas = new CanvasFrame("Web Cam");
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setCanvasSize(img.width(), img.height());

        while (true) {
            if (!canvas.isVisible()) {
                break;
            }

            frame = grabber.grab();

            if (frame == null) {
                break;
            }

            canvas.showImage(getImageForCanvas(frame));
        }

        grabber.stop();
        canvas.dispose();

        canvas.dispatchEvent(new WindowEvent(canvas, WindowEvent.WINDOW_CLOSING));
    }

    private BufferedImage getImageForCanvas(Frame frame) {
        BufferedImage bufferedImage = frameConverter.getBufferedImage(frame);

        return flipHorizontally(bufferedImage);
    }

    private BufferedImage flipHorizontally(BufferedImage originalImage) {
        // Créer une transformation pour inverser l'image horizontalement
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-originalImage.getWidth(), 0);

        // Appliquer la transformation à l'image
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(originalImage, null);
    }
}