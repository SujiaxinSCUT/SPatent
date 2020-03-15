package com.patent.service;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.patent.bean.Patent;


public interface IReadExcelService {

	ArrayList<Patent> ReadExcel(String path,int num) throws Exception;
}
