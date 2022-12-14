package sda.project.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sda.project.auction.model.Auction;
import sda.project.auction.model.File;
import sda.project.auction.repository.FileDBRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public List<File> store(MultipartFile[] files, Auction auction) throws IOException {
        List<File> storedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.getOriginalFilename().isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                File fileDB = new File(fileName, file.getContentType(), file.getBytes(), auction);

                storedFiles.add(fileDBRepository.save(fileDB));
            }
        }
        return storedFiles;
    }

    public File getFile(Long id) {
        return fileDBRepository.findById(id).get();
    }

    public List<File> getAllFiles() {
        return fileDBRepository.findAll();
    }

    public List<File> getFilesByAuctionId(Long id) {
        return fileDBRepository.getFilesByAuctionId(id);
    }

    public void delete(Long id) {
        fileDBRepository.deleteById(id);
    }

}