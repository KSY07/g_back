package io.gongson.g_back.service;

import io.gongson.g_back.repository.ConstructionExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConstructionExampleService {
    private final ConstructionExampleRepository constructionExampleRepository;

    public void createConstructionExample() {}
}
