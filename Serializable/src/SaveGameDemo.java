import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SaveGameDemo {

    // Сохраняем объект GameProgress в файл
    public static boolean saveGame(String filePath, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
            System.out.println("Сохранено: " + filePath);
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка сохранения " + filePath + " : " + e.getMessage());
            return false;
        }
    }

    // Упаковвываем список файлов в zip-архив
    public static boolean zipFiles(String zipPath, List<String> filesToZip) {
        try (FileOutputStream fos = new FileOutputStream(zipPath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            byte[] buffer = new byte[1024];

            for (String filePath : filesToZip) {
                File file = new File(filePath);
                if (!file.exists()) {
                    System.out.println("Файл не найден, пропускаем: " + filePath);
                    continue;
                }

                try (FileInputStream fis = new FileInputStream(file)) {
                    // В архив заносим только имя файла
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);

                    int len;
                    while ((len = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }

                    zos.closeEntry();
                    System.out.println("Записан в архив: " + file.getName());
                } catch (IOException e) {
                    System.out.println("Ошибка чтения файла " + filePath + " : " + e.getMessage());
                }
            }

            System.out.println("Архив создан: " + zipPath);
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка создания архива: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        String basePath = userHome + File.separator + "Games";
        String saveDir = basePath + File.separator + "savegames";

        String save1 = saveDir + File.separator + "save1.dat";
        String save2 = saveDir + File.separator + "save2.dat";
        String save3 = saveDir + File.separator + "save3.dat";

        // 1) Создаём три состояния игры
        GameProgress gp1 = new GameProgress(100, 2, 1, 10.5);
        GameProgress gp2 = new GameProgress(75, 5, 3, 254.7);
        GameProgress gp3 = new GameProgress(25, 1, 7, 1024.12);

        // 2) Сохраняем их
        List<String> createdFiles = new ArrayList<>();
        if (saveGame(save1, gp1)) createdFiles.add(save1);
        if (saveGame(save2, gp2)) createdFiles.add(save2);
        if (saveGame(save3, gp3)) createdFiles.add(save3);

        // 3) Упакуем все созданные файлы в zip
        String zipPath = saveDir + File.separator + "saves.zip";
        boolean zipped = zipFiles(zipPath, createdFiles);

        // 4) Если архив успешно создан, удаляем исходные файлы
        if (zipped) {
            for (String fPath : createdFiles) {
                File f = new File(fPath);
                if (f.exists()) {
                    if (f.delete()) {
                        System.out.println("Удалён исходный файл: " + fPath);
                    } else {
                        System.out.println("Не удалось удалить файл: " + fPath);
                    }
                }
            }
            System.out.println("Исходные файлы удалены (если они существовали).");
        } else {
            System.out.println("Архивация не удалась — исходные файлы оставлены.");
        }

        System.out.println("Готово.");
    }
}
