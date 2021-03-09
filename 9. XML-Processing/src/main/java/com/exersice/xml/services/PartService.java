package com.exersice.xml.services;

import com.exersice.xml.domain.dtos.importDtos.PartDto;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface PartService {
    void seedParts() throws JAXBException, FileNotFoundException;

    List<PartDto> getRandomParts();
}
