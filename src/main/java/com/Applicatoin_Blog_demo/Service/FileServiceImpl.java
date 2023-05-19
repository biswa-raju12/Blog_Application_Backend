package com.Applicatoin_Blog_demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FIleService{
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {


        //file name
        String name = file.getOriginalFilename();

        //Random Name generate file

        String randomID = UUID.randomUUID().toString();
        String fileName1= randomID.concat(name.substring(name.lastIndexOf(".")));


        //full path

        String fullPath= path + File.separator + fileName1;

//        create folder if not created

        File f= new File(path);

        if(!f.exists())
        {
            f.mkdir();
        }

        //File copy

        Files.copy(file.getInputStream(), Paths.get(fullPath));


        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String FullPath = path+File.separator + fileName;

        InputStream is = new FileInputStream(FullPath);

        return is;
    }
}
