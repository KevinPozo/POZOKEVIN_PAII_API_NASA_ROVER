/**
 *
 * @author KevinPozo
 * Title: Api Fotos de Rover Mars (Lambda).
 *
 *
 * */
package View;
import Controller.MarsApiController;
import Model.MarsPhoto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MarsPhotosViewerApp {

    private MarsApiController controller;
    private JFrame mainFrame;
    private JPanel rightPanel;
    private JComboBox<Integer> solSelection;
    private JButton searchButton;
    private JLabel statusLabel;
    private JSpinner numOfPhotosSpinner;

    private static final Integer[] preDefinedSols = IntStream.rangeClosed(50, 4000).boxed().toArray(Integer[]::new);

    public MarsPhotosViewerApp() {
        controller = new MarsApiController();

        mainFrame = new JFrame("[ Mars Photos Viewer ]");
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeUI();

        mainFrame.setVisible(true);
    }

    private void initializeUI() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JPanel searchPanel = createSearchPanel();
        JPanel photoDisplayPanel = createPhotoDisplayPanel();

        rightPanel.add(searchPanel, BorderLayout.WEST);
        rightPanel.add(photoDisplayPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Status: Ready");
        rightPanel.add(statusLabel, BorderLayout.SOUTH);

        mainFrame.add(rightPanel, BorderLayout.CENTER);
        rightPanel.setBackground(Color.GREEN);
        searchPanel.setBackground(Color.YELLOW);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel solLabel = new JLabel(">> Sol Range [0-4000]:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(solLabel, gbc);

        solSelection = new JComboBox<>(preDefinedSols);
        gbc.gridx = 1;
        gbc.gridy = 0;
        searchPanel.add(solSelection, gbc);

        JLabel roverLabel = new JLabel(">> Rover:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        searchPanel.add(roverLabel, gbc);

        String[] rovers = {"Curiosity", "Opportunity", "Spirit"};
        JComboBox<String> roverSelection = new JComboBox<>(rovers);
        gbc.gridx = 1;
        gbc.gridy = 1;
        searchPanel.add(roverSelection, gbc);

        JLabel cameraLabel = new JLabel(">> Camera:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        searchPanel.add(cameraLabel, gbc);

        String[] cameras = {"FHAZ", "RHAZ", "NAVCAM", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM_LEFT", "NAVCAM_RIGHT"};
        JComboBox<String> cameraSelection = new JComboBox<>(cameras);
        gbc.gridx = 1;
        gbc.gridy = 2;
        searchPanel.add(cameraSelection, gbc);

        JLabel numOfPhotosLabel = new JLabel(">> Number of Photos:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        searchPanel.add(numOfPhotosLabel, gbc);

        SpinnerModel spinnerModel = new SpinnerNumberModel(5, 1, 25, 1);
        numOfPhotosSpinner = new JSpinner(spinnerModel);
        gbc.gridx = 1;
        gbc.gridy = 3;
        searchPanel.add(numOfPhotosSpinner, gbc);

        searchButton = new JButton("[ Filter ]");
        searchButton.addActionListener(e -> {
            String rover = roverSelection.getSelectedItem().toString();
            String sol = solSelection.getSelectedItem().toString();
            int numOfPhotos = (Integer) numOfPhotosSpinner.getValue();
            String camera = cameraSelection.getSelectedItem().toString();

            searchPhotos(rover, sol, numOfPhotos, camera);
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchPanel.add(searchButton, gbc);

        return searchPanel;
    }

    private JPanel createPhotoDisplayPanel() {
        JPanel photoPanelContainer = new JPanel();
        photoPanelContainer.setLayout(new BoxLayout(photoPanelContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(photoPanelContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(scrollPane, BorderLayout.CENTER);
        containerPanel.setBackground(Color.black);
        return containerPanel;
    }

    private void searchPhotos(String rover, String sol, int numOfPhotos, String camera) {
        try {
            List<MarsPhoto> photos = controller.fetchMarsPhotos(rover, Integer.parseInt(sol), numOfPhotos, camera);
            displayPhotos(photos);
        } catch (IOException e) {
            setStatus("Error: Could not fetch photos from API.");
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Error: Could not fetch photos from API.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayPhotos(List<MarsPhoto> photos) {
        JScrollPane scrollPane = (JScrollPane) ((JPanel) rightPanel.getComponent(1)).getComponent(0);
        JPanel photoPanelContainer = (JPanel) scrollPane.getViewport().getView();
        photoPanelContainer.removeAll();
        if (photos.isEmpty()) {
            JLabel noPhotosLabel = new JLabel("We Not Found Photos With That Restrictions");
            photoPanelContainer.add(noPhotosLabel);
        } else {
            photos.forEach(photo -> {
                JPanel photoPanel = createPhotoPanel(photo);
                photoPanelContainer.add(photoPanel);
            });
        }
        photoPanelContainer.revalidate();
        photoPanelContainer.repaint();
    }


    private JPanel createPhotoPanel(MarsPhoto photo) {
        JPanel photoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] labels = {
                "[Identification]: " + photo.getId(),
                "[Sol Number]: " + photo.getSol(),
                "[Earth Date]: " + (photo.getEarthDate() != null ? photo.getEarthDate().format(DateTimeFormatter.ISO_DATE) : "N/A"),
                "[Rover Name]: " + photo.getRoverName(),
                "[Full Camera]: " + photo.getCameraFullName()
        };

        Arrays.stream(labels)
                .map(JLabel::new)
                .forEach(label -> {
                    photoPanel.add(label, gbc);
                    gbc.gridy++;
                });

        JButton viewOnWebButton = new JButton("[ Click to View On The Web ]");
        viewOnWebButton.addActionListener(e -> openWebPage(photo.getImgSrc()));
        gbc.gridx = 0;
        gbc.gridy++;
        photoPanel.add(viewOnWebButton, gbc);
        photoPanel.setBorder(BorderFactory.createEtchedBorder());
        photoPanel.setBackground(Color.CYAN);

        return photoPanel;
    }


    private void openWebPage(String url) {
        try {
            URI uri = new URI(url);
            Desktop.getDesktop().browse(uri);
        } catch (Exception ex) {
            handleException("Cannot open the link.", ex);
        }
    }

    private void handleException(String message, Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(mainFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    private void setStatus(String status) {
        statusLabel.setText("Status: " + status);
    }
}