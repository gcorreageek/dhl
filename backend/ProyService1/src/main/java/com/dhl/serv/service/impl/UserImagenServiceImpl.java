package com.dhl.serv.service.impl;

import com.dhl.serv.config.Constants;
import com.dhl.serv.service.UserImagenService;
import com.dhl.serv.domain.UserImagen;
import com.dhl.serv.repository.UserImagenRepository;
import com.dhl.serv.service.util.ImagesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.util.List;

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
        byte[] imageByteArray = ImagesUtil.decodeImage(result.getUserImagenPathImage());
        OutputStream out = null;
        try {
            System.getProperty("user.dir");
            out = new BufferedOutputStream(new FileOutputStream(Constants.PATH_PROJECT_IMAGE_UPLOAD+result.getId()+".png"));
            out.write(imageByteArray);
        } finally {
            if (out != null) out.close();
        }
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
