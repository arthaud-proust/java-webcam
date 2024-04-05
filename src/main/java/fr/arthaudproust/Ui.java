package fr.arthaudproust;

import org.bytedeco.javacv.CanvasFrame;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Ui {
    private CanvasFrame canvas;
    private boolean isFirstImageUpdate = true;

    public void open() {
        canvas = new CanvasFrame("Web Cam");
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showImage(BufferedImage image) {
        if (isFirstImageUpdate) {
            isFirstImageUpdate = false;
            canvas.setCanvasSize(image.getWidth(), image.getHeight());
        }

        canvas.showImage(image);
    }

    public boolean isOpen() {
        return canvas.isVisible();
    }

    public void close() {
        canvas.dispose();

        canvas.dispatchEvent(new WindowEvent(canvas, WindowEvent.WINDOW_CLOSING));
    }
}
