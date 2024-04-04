package fr.arthaudproust.webcam;


public class Main {

    private static final Webcam webcam = new Webcam();

    public static void main(String[] args) throws Exception {
        webcam.run();
    }
}