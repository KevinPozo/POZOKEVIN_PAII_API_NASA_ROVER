/**
 *
 * @author KevinPozo
 * Title: Api Fotos de Rover Mars (Lambda).
 *
 *
 * */
import View.MarsPhotosViewerApp;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MarsPhotosViewerApp());
    }
}
