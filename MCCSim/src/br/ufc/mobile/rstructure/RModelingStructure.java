/*******************************************************************************
 *
 *  *  * Copyright (C) 2015 Luis Sérgio da Silva Júnior
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *******************************************************************************/



package br.ufc.mobile.rstructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.rosuda.JRI.REXP;

public class RModelingStructure {
	
	
	public static List<Double> getValueFromDistribution(String rFile,String numberElements,String company, String location, String networkType, String dataTransfer){
		RTool rTool = RTool.getInstance();
		REXP rExp = rTool.callScript(rFile, numberElements,company, location, networkType, dataTransfer);
		
		double doubles[]= rExp.asDoubleArray();
		
		List<Double> doublesList = new ArrayList<Double>();
		for (Double doubleNumber : doubles) {
			doublesList.add(doubleNumber);
		}
		
		rTool.finalizeRPackageExecution();
		rTool = null;
		return doublesList;
	}

	
}
