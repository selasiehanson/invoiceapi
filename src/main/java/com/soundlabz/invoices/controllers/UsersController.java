package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.domain.UserCompany;
import com.soundlabz.invoices.domain.repositories.UserCompanyRepository;
import com.soundlabz.invoices.domain.repositories.UserRepository;
import com.soundlabz.invoices.security.UserAuthentication;
import com.soundlabz.invoices.services.FileManager;
import com.soundlabz.invoices.utils.ImageTypeAssit;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UsersController extends BaseController {

    private UserRepository userRepository;

    private FileManager fileManager;

    private UserCompanyRepository userCompanyRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserCompanyRepository(UserCompanyRepository userCompanyRepository) {
        this.userCompanyRepository = userCompanyRepository;
    }

    @Autowired
    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
    public User getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            return ((UserAuthentication) authentication).getDetails();
        }

        return new User(authentication.getName());
    }

    @RequestMapping(value = "/api/users/company-details", method = RequestMethod.POST)
    public UserCompany updateCompanyDetails(@RequestBody @Valid UserCompany userCompany) {
        userCompany.setUser(getCurrentUser());
        userCompanyRepository.save(userCompany);
        return userCompany;
    }

    @RequestMapping(value = "/api/users/upload-logo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void uploadLogo(@RequestParam("file") MultipartFile file, String username) throws IOException {
        String filename;

        if (!file.isEmpty()) {
            String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
            filename = fileManager.uploadFile(file, ext);
            UserCompany userCompany = userCompanyRepository.findByUserId(getCurrentUser().getId());
            userCompany.setLogo(filename);
            userCompanyRepository.save(userCompany);
        }
    }

    @RequestMapping(value = "/api/users/logo", method = RequestMethod.GET)
    public Map<String, String> getLogo() {
        Long userId = getCurrentUser().getId();
        String logoFilename = userCompanyRepository.findByUserId(userId).getLogo();

        try {
            byte[] bytes = fileManager.getBytes(logoFilename);

            String encodedImageStr = org.apache.tomcat.util.codec.binary.StringUtils.newStringUtf8(Base64.encodeBase64(bytes));
            String prefix = ImageTypeAssit.basePrefixFromFilename(logoFilename);
            String imageUri = String.format("%s,%s", prefix, encodedImageStr);

            Map<String, String> dataUri = new HashMap<>();
            dataUri.put("logo", imageUri);
            return dataUri;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

