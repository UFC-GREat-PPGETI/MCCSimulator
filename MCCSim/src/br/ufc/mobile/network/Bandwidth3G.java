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


package br.ufc.mobile.network;

import java.util.List;

import br.ufc.mobile.rstructure.RModelingStructure;

public class Bandwidth3G extends Bandwidth {
	//private String rFile = "networkRandomGenerator.R";
	private String company;
	private String location;
	private String networkType;

	//protected static final String transferTypeDown = "down";
	//protected static final String transferTypeUp = "up";

	public Bandwidth3G(String company, String location
			) {
		this.company = company;
		this.location = location;
		this.networkType = "3g";
		loadListValues();
	}

	private void loadListValues() {
		try{
		List<Double> doubleDownValues = RModelingStructure
				.getValueFromDistribution("networkRandomGenerator.R", "100",
						company, location, networkType, "down");
		downloadValues.addAll(doubleDownValues);

		List<Double> doubleUpValues = RModelingStructure
				.getValueFromDistribution("networkRandomGenerator.R", "100",
						company, location, networkType, "up");
		uploadValues.addAll(doubleUpValues);
		}
		catch(Exception e){
			System.err.println("problems during R script loading");
		}

	}

	@Override
	public long getCurrentLatency() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMByteCurrentDownloadBandwidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMByteCurrentUploadBandwidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getGByteCurrentDownloadBandwidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getGByteCurrentUploadBandwidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getByteCurrentDownloadBandwidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getByteCurrentUploadBandwidth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
