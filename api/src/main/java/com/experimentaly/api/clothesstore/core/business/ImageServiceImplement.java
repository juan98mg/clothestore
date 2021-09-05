package com.experimentaly.api.clothesstore.core.business;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javax.imageio.ImageIO;
import com.experimentaly.api.clothesstore.core.exception.BaseException;
import com.experimentaly.api.clothesstore.core.exception.ImageRerenderException;
import com.experimentaly.api.clothesstore.core.exception.ValidationException;
import com.experimentaly.api.clothesstore.core.ports.input.ImageService;
import com.experimentaly.api.clothesstore.core.util.AppConstants;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ImageEntity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.entity.ProductEntity;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository.ImageRepository;
import com.experimentaly.api.clothesstore.infrastructure.persistence.jpa.repository.ProductRepository;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImplement implements ImageService {


    @Value("#{'${app.api.allowed.file-extention}'.split(';')}")
    private String[] imageExtensions;

    @Value("${app.api.allowed.file-size}")
    private long maxSizeAllowed;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository repository;

    @Override
    public void save(MultipartFile[] files, ProductEntity product) {

        if (files != null) {

            try {

                var msnValid = validateToSave(files, product.getId());

                if (!"".equals(msnValid))
                    throw new ValidationException(AppConstants.IMAGES_VALIDATION_ERROR_CODE,
                            msnValid);


                for (MultipartFile file : files) {

                    if (file != null) {

                        var data = file.getSize() > maxSizeAllowed ? resize(file) : file.getBytes();
                        var fileName = file.getOriginalFilename();

                        fileName = fileName == null ? "provitional" : fileName;
                        var image = new ImageEntity(getName(fileName, product.getName()), data);
                        image.setProduct(product);

                        repository.saveAndFlush(image);
                    }
                }
            } catch (IOException io) {
                throw new BaseException(AppConstants.IMAGE_SAVE_ERROR,
                        AppConstants.ERROR_GETTING_BYTES, io);
            } catch (Exception e) {
                throw new BaseException(AppConstants.IMAGE_SAVE_ERROR, AppConstants.UNKNOW_ERROR,
                        e);
            }
        }
    }


    private String validateToSave(MultipartFile[] files, UUID productId) {

        StringBuilder str = new StringBuilder();

        if (files != null) {

            for (MultipartFile file : files) {

                if (file != null) {

                    var allowed = verifyAllowFile(file);

                    if (!allowed) {
                        str.append(String.format(AppConstants.NOT_ALLOWED_FILE,
                                file.getOriginalFilename()) + ";");
                    }
                }
            }

            if (!productRepository.existsById(productId))
                str.append(AppConstants.PRODUCT_DOES_NOT_EXIST_BY_ID);

        }
        return str.toString();

    }


    private boolean verifyAllowFile(MultipartFile file) {

        var allowed = false;

        if (file != null) {

            for (String ext : imageExtensions) {

                var name = file.getOriginalFilename();
                if (name != null && name.contains(ext)) {
                    allowed = true;
                    break;
                }
            }
        }

        return allowed;

    }

    private byte[] resize(MultipartFile file) {

        try {
            var standarHeight = 200;
            var standarWidth = 200;

            InputStream stream = new ByteArrayInputStream(file.getBytes());

            BufferedImage buffered =
                    simpleResizeImage(ImageIO.read(stream), standarWidth, standarHeight);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            ImageIO.write(buffered, extension, baos);
            return baos.toByteArray();

        } catch (IOException e) {
            throw new ImageRerenderException(AppConstants.RERENDER_FAILED_CODE,
                    AppConstants.RERENDER_FAILED);
        }



    }


    BufferedImage simpleResizeImage(BufferedImage originalImage, int targetWidth,
            int targetHeight) {
        return Scalr.resize(originalImage, targetWidth, targetHeight);
    }


    private String getName(String fileName, String productName) {

        fileName = StringUtils.cleanPath(fileName);

        return String.format(AppConstants.GENERIC_NAME, productName, System.currentTimeMillis(),
                fileName);


    }


    @Override
    public void changeImages(MultipartFile[] files, ProductEntity productEntity) {

        this.repository.deleteById(productEntity.getId());

        this.save(files, productEntity);

    }
}
