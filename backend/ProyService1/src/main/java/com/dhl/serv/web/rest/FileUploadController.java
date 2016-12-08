package com.dhl.serv.web.rest;


import com.dhl.serv.domain.User;
import com.dhl.serv.domain.UserImagen;
import com.dhl.serv.security.SecurityUtils;
import com.dhl.serv.service.UserImagenService;
import com.dhl.serv.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final Logger log = LoggerFactory.getLogger(UserImagenResource.class);

    @Inject
    private UserImagenService userImagenService;
    @Inject
    UserService userService;

//    private final StorageService storageService;

//    @Autowired
//    public FileUploadController(StorageService storageService) {
//        this.storageService = storageService;
//    }

//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException {
//
////        model.addAttribute("files", storageService
////            .loadAll()
////            .map(path ->
////                MvcUriComponentsBuilder
////                    .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
////                    .build().toString())
////            .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }
//
//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity
//            .ok()
//            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
//            .body(file);
//    }

    //    @PostMapping("/upload/")
    @RequestMapping(value = "/user-imagens/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("login") String login) {
        log.info("" + login);
        log.info("" + file.getOriginalFilename());
        UserImagen saveImagen = new UserImagen();
        try {
            User user = userService.getUserWithAuthoritiesByLogin(login).get();
            saveImagen.setMultipartFile(file);
            saveImagen.setUserImagenPathImage("url_web2");
            saveImagen.setUserImagenPathImage("url_web2");
            saveImagen.setUserImagenName(file.getOriginalFilename());
            saveImagen.setUser(user);
            saveImagen = userImagenService.save(saveImagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String id = saveImagen.getId().toString();
        log.info("saveImagen.getId().toString():"+id);
        return "redirect:/api/user-imagens/"+id;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
