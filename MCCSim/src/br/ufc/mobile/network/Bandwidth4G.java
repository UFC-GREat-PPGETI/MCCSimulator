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

import java.util.Arrays;
import java.util.List;

import br.ufc.mobile.rstructure.RModelingStructure;

public class Bandwidth4G extends Bandwidth {
	//private String rFile = "networkRandomGenerator.R";
	private String company;
	private String location;
	private String networkType;

	//protected static final String transferTypeDown = "down";
	//protected static final String transferTypeUp = "up";

	public Bandwidth4G(String company, String location) {
		this.company = company;
		this.location = location;
		this.networkType = "4g";
		if (company.equals("tim") && location.equals("pici")) {
			downloadValues.addAll(Arrays.asList(236.482290,100.810959,186.512507,62.592532,205.715748,
					191.208694,213.514832,219.238961,143.811891,208.357888,
					185.076727,511.836053,205.853205,376.678873,259.089447,
					203.907585,223.424108,197.016488,230.325426,205.995910
					,240.011995,-130.731165,197.046956,275.644429,174.366845
					,180.574618,200.930058,170.763594,190.278465,-27.251534
					,237.467753,225.159261,180.081308,192.139451,211.858499
					,210.374078,177.577348,106.064289,186.766476,10.212022
					,218.414120,-1365.210365,231.284496,188.323694,238.352936
					,271.965947,-595.046395,235.214344,182.178102,1707.087915
					,115.643835,336.905181,217.920300,179.531325,201.382984
					,208.059147,197.748434,252.433023,161.509114,226.362882
					,84.745383,165.609867,187.720534,199.992624,-1732.118151
					,201.996120,28.839178,215.088715,192.118731,109.512320
					,200.990066,211.925697,220.498132,192.530873,231.424968
					,104.952554,221.257130,203.597410,193.755857,165.335266
					,221.482696,202.774995,232.216017,228.731133,206.174188
					,3.884029,221.502936,183.096489,63.254451,300.481210
					,447.880507,181.076368,208.433725,218.079330,60.657706
					,212.584814,205.298745,230.601660,202.532420,190.854600));
			uploadValues.addAll(Arrays.asList(273.8108,247.9266,298.6688,318.0762,379.8974,278.1328,269.7543,406.2381
					,307.8515,285.9241,362.0025,315.9252,270.4474,304.0073,322.7150,282.4454
					,296.4476,268.1457,266.3524,340.6947,270.7335,295.0899,305.8421,372.4926
					,354.6859,267.3902,286.2057,342.6641,341.9377,318.4935,336.6597,280.1796
					,375.3685,338.3997,309.6326,290.5629,338.6993,250.6085,281.6110,311.8546
					,274.3937,238.5039,367.9776,367.5507,269.6271,338.8066,357.1523,277.8642
					,277.6805,334.7382,335.0405,266.2672,300.2282,369.7178,339.5167,336.1908
					,366.0531,353.7653,294.6304,344.1953,300.8197,258.6882,301.2862,319.4185
					,271.2241,295.6208,299.3000,279.7246,282.9632,315.6015,372.5525,335.2389
					,292.9830,344.6140,262.2178,368.3522,318.2898,381.3675,376.1775,255.9530
					,315.4119,278.5710,361.8037,234.7054,366.7479,315.1871,347.3696,277.6952
					,301.9729,321.4026,257.1049,304.4525,400.7073,318.9026,334.7559,405.8210
					,352.2092,237.5482,370.3679,319.9140
));
		} else {
			loadListValues();
		}
	}

	private void loadListValues() {
		try {
			List<Double> doubleDownValues = RModelingStructure
					.getValueFromDistribution("networkRandomGenerator.R",
							"100", company, location, networkType, "down");
			downloadValues.addAll(doubleDownValues);

			List<Double> doubleUpValues = RModelingStructure
					.getValueFromDistribution("networkRandomGenerator.R",
							"100", company, location, networkType, "up");
			uploadValues.addAll(doubleUpValues);
		} catch (Exception e) {
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
