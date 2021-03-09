package com.exersice.xml.services;

import com.exersice.xml.domain.dtos.importDtos.SupplierDto;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SupplierService {

    void seedSuppliers() throws JAXBException, FileNotFoundException;

    SupplierDto getRandomSupplier();
}
