package com.mahi.demo.configuration;


import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Map;

@Component
public class CloudinaryConfig {
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
                .border("2px_solid_black").crop(action))
                .imageTag(name);

    }
    public void cropCircle()
    {
        cloudinary.url().transformation(new Transformation()
                .width(400).height(400).gravity("face").radius("max").crop("crop").chain()
                .width(200).crop("scale")).imageTag("lady.jpg");
    }

    public void textAdd()
    {
        cloudinary.url().transformation(new Transformation().overlay("text:Arial_45_bold:Hello%20World")
                .gravity("north_west").x(20).y(20)).imageTag("face_center.jpg");
    }

    public void textOverlay()
    {
        cloudinary.url().imageTag("black_bar.png");
    }
}