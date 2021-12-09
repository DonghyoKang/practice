package com.example.demo.service;

import com.example.demo.domain.entity.File;
import com.example.demo.domain.repository.FileRepository;
import com.example.demo.dto.BoardDto;
import com.example.demo.dto.FileDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FileService {
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileDto getFile(Long id) {
        if (id == null) {
            return FileDto.builder().origFilename("").build();
        }
        File file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return fileDto;
    }

    @Transactional
    public void deleteFile(Long id) {
        if (id != null) {
            fileRepository.deleteById(id);
        }
    }
}
