import Adapters.ApiAdapter;
import Models.WeatherData;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.net.URLEncoder;


/**
 * Weather App for Windows / Testing
 * Designed By : Sineth Janidu
 */
public class Main {
    private static JFrame jFrame;
    private static JPanel panel;
    private static JTextField searchField;
    private static JButton searchButton;
    private static WeatherData weatherData;
    private static  JPanel exPanel;
    private static JLabel cityLabel,imageClimate ,currentTemp,currentWind;

    private static JEditorPane editorPane;
    public static void main(String[] args) throws IOException {
        URL imageUrl = new URL("https://images.nationalgeographic.org/image/upload/t_edhub_resource_key_image/v1607339957/videos/posters/Extreme%20Weather:%20Droughts.jpg");
        Image backgroundImage = ImageIO.read(imageUrl);

        jFrame = new JFrame("Weather App");
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        addHeadingField("Weather App");
        addSearchField();
        addSearchButton();

        addexPanel();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (!searchText.isEmpty()) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            updateWeatherData();
                            return null;
                        }

                        @Override
                        protected void done() {
                            // Update UI on the EDT (Event Dispatch Thread)
                            SwingUtilities.invokeLater(() -> {
                                try {
                                    showData();
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(jFrame, "Error updating UI: " + ex.getMessage(),
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            });
                        }
                    };

                    worker.execute();
                }
            }
        });


        jFrame.add(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800,800);
        jFrame.setVisible(true);
    }



    static void addHeadingField(String heading) {
        JLabel headingLabel = new JLabel(heading);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel HeadingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        HeadingPanel.setOpaque(false);
        HeadingPanel.setMaximumSize(new Dimension(200, 40));
        Border bottomMargin = BorderFactory.createEmptyBorder(0, 20, 20, 0);
        headingLabel.setBorder(bottomMargin);
        HeadingPanel.add(headingLabel);
        panel.add(HeadingPanel);
    }

    static void addSearchField() {
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(700, 30));
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setOpaque(false);
        panel.add(searchField);
    }


    static void addSearchButton() {
        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(700,40));
        searchButton.setBackground(new Color(70, 130, 180));
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        searchButton.setFont(buttonFont);


        Border topMargin = BorderFactory.createEmptyBorder(20, 0, 20, 0);
        searchButton.setBorder(topMargin);
        searchButton.setBorderPainted(false);


        //New JPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE,40));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));
        buttonPanel.add(searchButton);
        buttonPanel.setOpaque(false);



        panel.add(buttonPanel);
    }


    private static void updateWeatherData() {
        ApiAdapter apiAdapter = new ApiAdapter(searchField.getText());
        weatherData = apiAdapter.getWeatherData();

    }

    static void addexPanel() throws IOException {
        exPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //City Label
        cityLabel = new JLabel();
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        cityLabel.setFont(buttonFont);
        cityLabel.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        //Image Lable
        imageClimate = new JLabel();

        //Current Temp
        currentTemp = new JLabel();
        currentTemp.setFont(buttonFont);
        currentTemp.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));

        //Curent Wind
        currentWind = new JLabel();
        currentWind.setFont(buttonFont);
        currentWind.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));


        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");

        editorPane.setVisible(false);

        exPanel.setOpaque(false);

        exPanel.add(cityLabel);
        exPanel.add(imageClimate);

        exPanel.add(currentTemp);
        exPanel.add(currentWind);
        exPanel.add(editorPane);

        panel.add(exPanel);
    }
    static void showData() throws IOException {

        try {
            //City Label
            cityLabel.setText("Location : " + weatherData.getLocation().getName()
                    + "," + weatherData.getLocation().getRegion()
                    + "," + weatherData.getLocation().getCountry());


            //Image
            String imageurl = "https:"+ weatherData.getCurrent().getCondition().getIcon();


            URL imageUrl = new URL(imageurl);
            Image image = ImageIO.read(imageUrl);

            // Scale the image to fit within the JLabel
            Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            imageClimate.setIcon(new ImageIcon(scaledImage));

            //Current Temperate
            currentTemp.setText("Current Temperature : "
                    + String.valueOf(weatherData.getCurrent().getTemp_c())
                    + " \u00B0C / "
                    + weatherData.getCurrent().getTemp_f()
                    + " \u00B0F" );


            //CUrent Wind
            currentWind.setText("Current Wind : "
                    + weatherData.getCurrent().getWind_kph()
                    + " KmPh / "
                    + weatherData.getCurrent().getWind_mph()
                    + " Mph");


            double latitude = weatherData.getLocation().getLat();
            double longitude =  weatherData.getLocation().getLon();
            String markerUrl = "markers=" + URLEncoder.encode("color:red|label:L|" + latitude + "," + longitude, "UTF-8");
            String mapUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude +
                    "&zoom=12&size=700x400&" + markerUrl + "&key=API_KEY";
            String htmlContent = "<html><body><img src='" + mapUrl + "'></body></html>";
            // Load HTML content into the editorPane
            editorPane.setContentType("text/html");
            editorPane.setText(htmlContent);
            editorPane.setVisible(true);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }


}
