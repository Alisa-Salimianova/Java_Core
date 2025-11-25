import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameInstaller {

    public static void main(String[] args) {

        String basePath = "/home/alisa/Games";  // путь к моей папке

        StringBuilder log = new StringBuilder();

        // Создаем массив директорий, которые нужно создать
        String[] dirs = {
                basePath + "/src",
                basePath + "/res",
                basePath + "/savegames",
                basePath + "/temp",
                basePath + "/src/main",
                basePath + "/src/test",
                basePath + "/res/drawables",
                basePath + "/res/vectors",
                basePath + "/res/icons"
        };

        // Создаём директории
        for (String dirPath : dirs) {
            File dir = new File(dirPath);
            if (dir.mkdirs()) {
                log.append("Директория создана: ").append(dirPath).append("\n");
            } else {
                log.append("Не удалось создать директорию: ").append(dirPath).append("\n");
            }
        }

        // Создаём файлы
        String[] files = {
                basePath + "/src/main/Main.java",
                basePath + "/src/main/Utils.java",
                basePath + "/temp/temp.txt"
        };

        for (String filePath : files) {
            File file = new File(filePath);
            try {
                if (file.createNewFile()) {
                    log.append("Файл создан: ").append(filePath).append("\n");
                } else {
                    log.append("Не удалось создать файл: ").append(filePath).append("\n");
                }
            } catch (IOException e) {
                log.append("Ошибка при создании файла: ").append(filePath)
                        .append(" — ").append(e.getMessage()).append("\n");
            }
        }

        // Записываем лог в temp.txt
        try (FileWriter writer = new FileWriter(basePath + "/temp/temp.txt")) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println("Ошибка записи лога: " + e.getMessage());
        }

        System.out.println("Установка завершена. Проверьте папку Games.");
    }
}
