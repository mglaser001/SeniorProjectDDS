package com.DSS.Processors;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

abstract public class BaseProcessor {

	public void intitialize() {}
	public void process()  throws DocumentException, IOException {}
	
}
