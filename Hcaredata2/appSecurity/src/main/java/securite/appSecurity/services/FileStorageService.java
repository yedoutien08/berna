package securite.appSecurity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import securite.appSecurity.Exceptions.MyException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileStorageService {
    private Path uploadFileDir;

    @Autowired
    public void FileSystemStorageService(@Value("${app.upload-dir}") String uploadDir) {
        this.uploadFileDir = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadFileDir);
        } catch (IOException ex) {
            throw new MyException("Impossible de créer le répertoire de stockage");
        }
    }

    public String storeFile(MultipartFile file) {
        // Générer un nom de fichier unique
        //String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        String fileName = file.getOriginalFilename();
        // Créer le chemin complet de l'emplacement cible où le fichier sera stocké
        Path targetLocation = this.uploadFileDir.resolve(fileName);

        try {

            // Copier le contenu du fichier (obtenu à partir de file.getInputStream()) vers l'emplacement cible
            // REPLACE_EXISTING : Remplace le fichier existant s'il y en a un avec le même nom
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            // Utilisez relativize pour obtenir le chemin relatif par rapport au répertoire de base
            Path relativePath = uploadFileDir.relativize(targetLocation);
            System.out.println("Path "+relativePath.toString());
            //return fileName;
            return relativePath.toString();
        } catch (IOException ex) {
            throw new MyException("Impossible de stocker le fichier " + fileName);
        }
    }
}
