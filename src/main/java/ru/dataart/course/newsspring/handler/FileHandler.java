package ru.dataart.course.newsspring.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.dataart.course.newsspring.entity.News;
import ru.dataart.course.newsspring.exceptions.InputFileException;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.zip.ZipInputStream;

@Component
public class FileHandler {
    public News openFile(MultipartFile file) {
        if (!Objects.requireNonNull(file.getOriginalFilename()).contains(".zip")) {
            throw new InputFileException("The file is not an archive");
        }
        return openZipFile(file);
    }

    private News openZipFile(MultipartFile file) {
        StringBuilder title = new StringBuilder();
        StringBuilder body = new StringBuilder();
        if (!file.isEmpty()) {
            try {
                InputStream inputStream = file.getInputStream();
                ZipInputStream zipInputStream = new ZipInputStream(inputStream);
                boolean isBody = false;
                int count = 0;
                while (zipInputStream.getNextEntry() != null) {
                    if (count > 0) {
                        throw new InputFileException("File has more than 1 files");
                    }
                    int i;
                    while ((i = zipInputStream.read()) != -1) {
                        if (!isBody) {
                            title.append((char) i);
                        } else {
                            if(i=='\n'){
                                body.append("</p><p>");
                            }else {
                                body.append((char) i);
                            }
                        }
                        if (i == '\n') {
                            isBody = true;
                            body.append("<p>");
                        }
                    }
                    body.append("</p>");
                    zipInputStream.closeEntry();
                    count++;
                }
                inputStream.close();
                zipInputStream.close();
            } catch (InputFileException e) {
                throw e;
            } catch (Exception e) {
                throw new InputFileException("Stream Exp");
            }
        } else {
            throw new InputFileException("File is Empty");
        }
        return new News(title.toString(), body.toString(), LocalDateTime.now(ZoneId.of("UTC")));
    }
}
