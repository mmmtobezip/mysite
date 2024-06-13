package com.poscodx.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
  private static String SAVE_PATH = "/fileupload-files"; // 배포되는 리눅스에 맞게 경로 설정 필요
  private static String URL_PATH = "/images"; // 가상 url

  public String restore(MultipartFile file) {
    String url = null;
    try {
      // 1. 저장할 directory 생성
      File uploadDirectory = new File(SAVE_PATH);
      if (!uploadDirectory.exists()) {
        uploadDirectory.mkdirs(); // mkdirs(), 하나는 mkdir()
      }

      if (file.isEmpty()) {
        return url; //
      }

      // 2. 파일 처리
      String originFilename = file.getOriginalFilename();
      // 확장자 제거
      String extName = originFilename.substring(originFilename.lastIndexOf(".") + 1); // .부터 마지막까지
      String saveFilename = generateSaveFilename(extName);
      Long fileSize = file.getSize();

      System.out.println("########" + originFilename);
      System.out.println("########" + saveFilename);
      System.out.println("########" + fileSize);

      byte[] data = file.getBytes();
      OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
      os.write(data);
      os.close();

      url = URL_PATH + "/" + saveFilename;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return url;
  }

  private static String generateSaveFilename(String extName) {
    String filename = "";

    Calendar calendar = Calendar.getInstance();
    filename += calendar.get(Calendar.YEAR);
    filename += calendar.get(Calendar.MONTH);
    filename += calendar.get(Calendar.DATE);
    filename += calendar.get(Calendar.HOUR);
    filename += calendar.get(Calendar.MINUTE);
    filename += calendar.get(Calendar.SECOND);
    filename += calendar.get(Calendar.MILLISECOND);
    filename += ("." + extName);
    return filename;
  }
}
