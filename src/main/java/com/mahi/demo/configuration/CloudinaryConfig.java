package com.mahi.demo.configuration;


import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import com.mahi.demo.models.Uploads;
import com.mahi.demo.repository.UploadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Map;

@Component
public class CloudinaryConfig {
    @Autowired
    UploadsRepository uploadsRepository;
    private Cloudinary cloudinary;

    @Autowired
    public CloudinaryConfig(@Value("${cloudinary.apikey}") String key,
                            @Value("${cloudinary.apisecret}") String secret,
                            @Value("${cloudinary.cloudname}") String cloud){

        cloudinary= Singleton.getCloudinary();
        cloudinary.config.cloudName=cloud;
        cloudinary.config.apiSecret=secret;
        cloudinary.config.apiKey=key;
    }

    public Map upload(Object file, Map options){
        try{
            return  cloudinary.uploader().upload(file, options);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public String createUrl(String name, int width, int height, String action){

        return cloudinary.url()
                .transformation(new Transformation().width(width).height(height)
                        .effect("sharpen"))
                .imageTag(name);
    }

}
