package com.zakaflow.zakaflow.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

    String store(MultipartFile file, String subfolder);

    List<String> storeAll(MultipartFile[] files, String subfolder);
}
