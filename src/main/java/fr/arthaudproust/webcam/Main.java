package fr.arthaudproust.webcam;


import java.awt.image.BufferedImage;

public class Main {

    private static final Ui ui = new Ui();
    private static final Webcam webcam = new Webcam();

    public static void main(String[] args) throws Exception {
        webcam.startRecording();
        ui.open();

        while (true) {
            if (!ui.isOpen()) {
                break;
            }

            BufferedImage frame = webcam.getFrame();

            if (frame == null) {
                break;
            }

            ui.showImage(frame);
        }

        webcam.stopRecording();
        ui.close();
    }
}