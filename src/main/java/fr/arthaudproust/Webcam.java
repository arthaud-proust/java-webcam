package fr.arthaudproust;


import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Webcam {
    private final FrameGrabber grabber = new OpenCVFrameGrabber(0);
    private final Java2DFrameConverter frameConverter = new Java2DFrameConverter();

    public void startRecording() throws FrameGrabber.Exception {
        grabber.start();
    }

    public void stopRecording() throws FrameGrabber.Exception {
        grabber.stop();
    }

    public BufferedImage getFrame() throws FrameGrabber.Exception {
        Frame frame = grabber.grab();
        BufferedImage bufferedImage = frameConverter.getBufferedImage(frame);

        return flipHorizontally(bufferedImage);
    }

    private BufferedImage flipHorizontally(BufferedImage originalImage) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-originalImage.getWidth(), 0);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(originalImage, null);
    }
}