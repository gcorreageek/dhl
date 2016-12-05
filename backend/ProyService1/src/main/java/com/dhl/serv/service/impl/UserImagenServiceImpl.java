package com.dhl.serv.service.impl;

import com.dhl.serv.config.Constants;
import com.dhl.serv.service.UserImagenService;
import com.dhl.serv.domain.UserImagen;
import com.dhl.serv.repository.UserImagenRepository;
import com.dhl.serv.service.util.ImagesUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.util.List;

import static com.dhl.serv.config.Constants.*;

/**
 * Service Implementation for managing UserImagen.
 */
@Service
@Transactional
public class UserImagenServiceImpl implements UserImagenService{

    private final Logger log = LoggerFactory.getLogger(UserImagenServiceImpl.class);

    @Inject
    private UserImagenRepository userImagenRepository;

    /**
     * Save a userImagen.
     *
     * @param userImagen the entity to save
     * @return the persisted entity
     */
    public UserImagen save(UserImagen userImagen) throws IOException {
        log.debug("Request to save UserImagen : {}", userImagen);
        UserImagen result = userImagenRepository.save(userImagen);

        if(result.getUserImagenPathImage().equals("url_web")){

            String path_pc = PATH_PROJECT_IMAGE_UPLOAD+result.getId()+".jpeg";
            String url_web = new StringBuilder().append(PATH_PROJECT_IMAGE_UPLOAD_WEB).append(result.getId()).append(".jpeg").toString();


            File file = new File(PATH_PROJECT_IMAGE_USER_DEFAULT_NEW);

            File file2 = new File(path_pc);

            boolean success = file.renameTo(file2);
            if(!success){
                log.error("No se pudo reenombrar la imagen");
                throw new java.io.IOException("No se pudo reenombrar la imagen");
            }

            File source = new File(PATH_PROJECT_IMAGE_USER_DEFAULT);
            File dest = new File(PATH_PROJECT_IMAGE_USER_DEFAULT_NEW);

            FileUtils.copyFile(source, dest);



            result.setUserImagenPath(path_pc);
            result.setUserImagenPathImage(url_web);

        }else{
            byte[] imageByteArray = ImagesUtil.decodeImage(result.getUserImagenPathImage());
            OutputStream out = null;
            try {
                String path_pc = PATH_PROJECT_IMAGE_UPLOAD+result.getId()+".png";
                String url_web = new StringBuilder().append(PATH_PROJECT_IMAGE_UPLOAD_WEB).append(result.getId()).append(".png").toString();
                result.setUserImagenPath(path_pc);
                result.setUserImagenPathImage(url_web);

                out = new BufferedOutputStream(new FileOutputStream(path_pc));
                out.write(imageByteArray);
            } finally {
                if (out != null) out.close();
            }
        }

        userImagenRepository.save(result);
        return result;
    }

    /**
     *  Get all the userImagens.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<UserImagen> findAll() {
        log.debug("Request to get all UserImagens");
        List<UserImagen> result = userImagenRepository.findAll();

        return result;
    }
    @Transactional(readOnly = true)
    public List<UserImagen> findByUserIsCurrentUser() {
        log.debug("Request to get all UserImagens");
        List<UserImagen> result = userImagenRepository.findByUserIsCurrentUser();

        return result;
    }

    /**
     *  Get one userImagen by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public UserImagen findOne(Long id) {
        log.debug("Request to get UserImagen : {}", id);
        UserImagen userImagen = userImagenRepository.findOne(id);
        return userImagen;
    }

    /**
     *  Delete the  userImagen by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserImagen : {}", id);
        userImagenRepository.delete(id);
    }
}
