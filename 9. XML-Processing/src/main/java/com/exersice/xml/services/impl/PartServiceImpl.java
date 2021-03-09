package com.exersice.xml.services.impl;

import com.exersice.xml.domain.dtos.importDtos.PartDto;
import com.exersice.xml.domain.dtos.importDtos.PartRootDto;
import com.exersice.xml.domain.entities.Part;
import com.exersice.xml.repositories.PartRepository;
import com.exersice.xml.services.PartService;
import com.exersice.xml.services.SupplierService;
import com.exersice.xml.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private final static String XML_PART_FILE_PATH =
            "C:\\SoftUni\\DB\\xmlexersice\\src\\main\\resources\\xml\\parts.xmlD:\\My Documents\\Java DB\\Hibernate\\8.JSON PROCESSING\\xml exersice\\src\\main\\resources\\xmls\\parts.xml";

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final SupplierService supplierService;
    private final XmlParser xmlParser;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, SupplierService supplierService, XmlParser xmlParser) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedParts() throws JAXBException {
        PartRootDto list = this.xmlParser.parseXml(PartRootDto.class, XML_PART_FILE_PATH);

        for (PartDto partDto: list.getPartDtos()) {
            partDto.setSupplierDto(this.supplierService.getRandomSupplier());

            this.partRepository.saveAndFlush(this.modelMapper.map(partDto, Part.class));
        }
    }

    @Override
    public List<PartDto> getRandomParts() {
        List<PartDto> partDtos = new ArrayList<>();

        Random random = new Random();

        int size = random.nextInt(10) + 10;

        for (int i = 0; i < size ; i++) {
            int id = random.nextInt((int) (this.partRepository.count()) - 1) + 1;
            PartDto part = this.modelMapper.map(this.partRepository.getOne(id),PartDto.class);
            part.setSupplierDto(this.supplierService.getRandomSupplier());

            partDtos.add(part);
        }

        return partDtos;
    }
}