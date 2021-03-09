package com.exersice.xml.services.impl;

import com.exersice.xml.domain.dtos.importDtos.SupplierDto;
import com.exersice.xml.domain.dtos.importDtos.SupplierRootDto;
import com.exersice.xml.domain.entities.Supplier;
import com.exersice.xml.repositories.SupplierRepository;
import com.exersice.xml.services.SupplierService;
import com.exersice.xml.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Random;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final static String XML_SUPPLIER_FILE_PATH =
            "D:\\My Documents\\Java DB\\Hibernate\\8.JSON PROCESSING\\xml exersice\\src\\main\\resources\\xmls\\suppliers.xml";


    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSuppliers() throws JAXBException {

        SupplierRootDto list =  this.xmlParser.parseXml(SupplierRootDto.class, XML_SUPPLIER_FILE_PATH);

        for (SupplierDto supplierDto: list.getSupplierDtos()) {
            this.supplierRepository.saveAndFlush(this.modelMapper.map(supplierDto, Supplier.class));
        }
    }

    @Override
    public SupplierDto getRandomSupplier() {
        Random random = new Random();

        int id = random.nextInt((int) (this.supplierRepository.count()) - 1) +1 ;

        Supplier supplier = this.supplierRepository.getOne(id);

        return this.modelMapper.map(supplier,SupplierDto.class);
    }
}
