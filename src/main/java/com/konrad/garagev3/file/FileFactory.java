package com.konrad.garagev3.file;

import com.konrad.garagev3.file.model.FileType;
import com.konrad.garagev3.file.strategy.FileStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FileFactory {
    @Autowired
    private List<FileStrategy> fileStrategy;
    private Map<FileType, FileStrategy> fileStrategyMap;

    @PostConstruct
    public void init(){
       fileStrategyMap = fileStrategy.stream().collect(Collectors.toMap(k -> k.getFileType(), k -> k));
    }

    public FileStrategy getStrategy(FileType fileType) {
        return fileStrategyMap.get(fileType);
    }
}
