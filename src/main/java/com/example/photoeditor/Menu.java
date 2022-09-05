package com.example.photoeditor;

import com.example.photoeditor.image.ImageType;
import com.example.photoeditor.image.Photo;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Menu {
    private static Menu menu;
    public static final FileChooser fileChooser = new FileChooser();
    private final ImageType[] imageTypes = ImageType.values();
    Photo photo = Photo.getInstance();

    static {
        configuringFileChooser();
    }

    private Menu() {
    }

    public static synchronized Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public void openImage(Button button, ImageView imageView) {
        File pathImage = fileChooser.showOpenDialog(button.getScene().getWindow());

        if (pathImage != null) {
            if (checkTypeImage(pathImage)) {
                photo.setImage(new Image(String.valueOf(pathImage.toURI()), true));
                imageView.setImage(photo.getImage());
                imageView.setViewport(new Rectangle2D(imageView.xProperty().get(), imageView.yProperty().get(),
                        photo.getWidth(), photo.getHeight()));
            }
        }
    }

    public void saveImage(Button button) throws IOException {
        if (photo.getImage() != null) {
            fileChooser.setInitialFileName("Безымянный");
            File file = fileChooser.showSaveDialog(button.getScene().getWindow());
            if (file != null) {
                ImageIO.write(SwingFXUtils.fromFXImage(photo.getImage(), null), getTypeImage(file), file);
            }
        }
    }

    public void clearImageField(ImageView imageView) {
        photo.setImage(null);
        imageView.setImage(null);
    }

    private boolean checkTypeImage(File pathImage) {
        for (ImageType type : imageTypes) {
            if (pathImage.toString().matches("(.*)" + type.toString().toLowerCase() + "$")) {
                return true;
            }
        }
        return false;
    }

    public void applyChanges(ImageView imageView) {
        photo.setImage(imageView.getImage());
    }

    public void cancelChanges(ImageView imageView) {
        imageView.setImage(photo.getImage());
    }

    private String getTypeImage(File file) {
        return file.getName().replaceAll(".*\\.(?=.*$)", "");
    }

    private static void configuringFileChooser() {
        Menu.fileChooser.setTitle("Select a Directory");
        Menu.fileChooser.setInitialDirectory(new File("C:/Users"));
        Menu.fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"));

    }
}
