package com.sqisoft.ssbr.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import voPackage.SophosInfoVO;
import voPackage.SophosMetaDataIndex;

public class DataParsingUtil {
	
	public SophosInfoVO getSophosInfoVO() {
		
		SophosInfoVO vo = new SophosInfoVO();
		try {
			
			BufferedReader in = new BufferedReader(new FileReader("/www/virusData/version.data"));
			String s;
			int i=0;
			
			while ((s = in.readLine()) != null) {
				if(i==0)
					;
				else{
					String dataParsing = s.split(":")[1];
					System.out.println(s + "/" + dataParsing);
					setSophosInfoVO(vo, i, dataParsing);
				}
				i++;
			}
		} 
		catch (FileNotFoundException e) {
		} 
		catch (IOException e) {
		}
		return vo;
	}
	
	private void setSophosInfoVO(SophosInfoVO vo, int i, String dataParsing) {
		switch(i){
			case SophosMetaDataIndex.ENGINE_VERSION:
				vo.setEngineVersion(dataParsing);
				break;
				
			case SophosMetaDataIndex.SAV_VERSION:
				vo.setSavVersion(dataParsing);
				break;
				
			case SophosMetaDataIndex.LIB_VERSION:
				vo.setLibraryVerson(dataParsing);
				break;
				
			case SophosMetaDataIndex.DETECTABLE_VIRUSES:
				vo.setNumOfDetectableVirus(dataParsing);
				break;
				
			case SophosMetaDataIndex.DATE_OF_VIRUS_DATA:
				vo.setDateOfVirusData(dataParsing);
				break;
		}
	}
}
